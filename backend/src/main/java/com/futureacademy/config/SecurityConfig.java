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

  @Bean public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

  @Bean
  public DaoAuthenticationProvider authenticationProvider(UserDetailsService uds, PasswordEncoder encoder) {
    DaoAuthenticationProvider p = new DaoAuthenticationProvider();
    p.setUserDetailsService(uds); p.setPasswordEncoder(encoder);
    return p;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/index.html", "/assets/**", "/favicon.ico").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/contactos").permitAll()
            .requestMatchers("/api/**").authenticated()
            .anyRequest().permitAll())
        .cors(cors -> {})
        .httpBasic(httpBasic -> {})
        // Evita que el navegador muestre el popup de BasicAuth
        .exceptionHandling(ex -> ex.authenticationEntryPoint((req, res, e) -> {
            // 401 sin cabecera WWW-Authenticate => NO popup
            res.setStatus(401);
        }));
    return http.build();
  }
}
