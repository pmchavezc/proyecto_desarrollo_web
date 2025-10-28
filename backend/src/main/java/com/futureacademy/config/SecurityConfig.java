package com.futureacademy.config;

import com.futureacademy.domain.entity.Usuario;
import com.futureacademy.domain.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

/**
 * Seguridad con Basic Auth:
 *  - POST /api/contactos es público (form de la landing)
 *  - Resto de /api/** requiere autenticación
 *  - CORS habilitado (Vite proxy en dev)
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
    return username -> {
      Optional<Usuario> opt = usuarioRepository.findByEmail(username);
      Usuario u = opt.orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
      return User.withUsername(u.getEmail())
              .password(u.getPasswordHash())
              .roles(u.getRol())
              .build();
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider(UserDetailsService uds, PasswordEncoder encoder) {
    DaoAuthenticationProvider p = new DaoAuthenticationProvider();
    p.setUserDetailsService(uds);
    p.setPasswordEncoder(encoder);
    return p;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    // estáticos ya permitidos
                    .requestMatchers("/", "/index.html", "/assets/**", "/favicon.ico").permitAll()

                    // 👇 Rutas del ReactAppController (SPA) — permitidas
                    .requestMatchers("/", "/login", "/dashboard", "/mis-tareas", "/tareas").permitAll()
                    // Nota: "/#contacto" no es necesario; el fragmento (#) no llega al servidor.

                    // público de la landing
                    .requestMatchers(HttpMethod.POST, "/api/contactos").permitAll()

                    // API protegida
                    .requestMatchers("/api/**").authenticated()

                    // el resto
                    .anyRequest().permitAll()
            )
            .cors(cors -> {
            })
            .httpBasic(httpBasic -> {
            })
            .exceptionHandling(ex -> ex.authenticationEntryPoint((req, res, e) -> res.setStatus(401)));
    return http.build();
  }
}