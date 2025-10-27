package com.futureacademy.controller;
import com.futureacademy.domain.entity.Estudiante; import com.futureacademy.domain.entity.Usuario;
import com.futureacademy.domain.service.UsuarioService; import com.futureacademy.dto.AuthMeResponse;
import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import java.util.Optional;
/** Endpoints de autenticación (Basic). */
@RestController @RequestMapping("/api/auth")
public class AuthController {
  private final UsuarioService usuarioService; public AuthController(UsuarioService u){this.usuarioService=u;}
  /** Devuelve info del usuario autenticado. */
  @GetMapping("/me") public AuthMeResponse me(Authentication auth){
    String email = auth.getName(); Usuario u = usuarioService.findByEmail(email).orElseThrow();
    Optional<Estudiante> e = usuarioService.findEstudianteByUsuario(u);
    AuthMeResponse r = new AuthMeResponse();
    r.usuarioId=u.getId(); r.email=u.getEmail(); r.rol=u.getRol();
    r.estudianteId=e.map(Estudiante::getId).orElse(null);
    r.nombres=e.map(Estudiante::getNombres).orElse(null);
    r.apellidos=e.map(Estudiante::getApellidos).orElse(null);
    return r;
  }
}
