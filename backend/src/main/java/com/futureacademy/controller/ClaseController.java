package com.futureacademy.controller;
import com.futureacademy.domain.entity.*; import com.futureacademy.domain.service.*;
import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import java.util.List;
/* Endpoints de clases/inscripciones. */
@RestController @RequestMapping("/api")
public class ClaseController {
  private final ClaseService claseService; private final UsuarioService usuarioService;
  public ClaseController(ClaseService c, UsuarioService u){this.claseService=c;this.usuarioService=u;}

  @GetMapping("/clases/activas")
  public List<Clase> activas(){return claseService.clasesActivas();}

  @GetMapping("/mis-clases")
  public List<Clase> misClases(Authentication auth){
    Usuario u = usuarioService.findByEmail(auth.getName()).orElseThrow();
    Estudiante e = usuarioService.findEstudianteByUsuario(u).orElseThrow(); return claseService.clasesInscritas(e);
  }
  @GetMapping("/clases/no-inscritas")
  public List<Clase> noInscritas(Authentication auth){
    Usuario u = usuarioService.findByEmail(auth.getName()).orElseThrow();
    Estudiante e = usuarioService.findEstudianteByUsuario(u).orElseThrow(); return claseService.clasesNoInscritasActivas(e);
  }
  @PostMapping("/inscripciones/{claseId}")
  public void inscribirse(Authentication auth, @PathVariable Long claseId){
    Usuario u = usuarioService.findByEmail(auth.getName()).orElseThrow();
    Estudiante e = usuarioService.findEstudianteByUsuario(u).orElseThrow(); claseService.inscribir(e, claseId);
  }
}
