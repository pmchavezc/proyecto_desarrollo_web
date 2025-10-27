package com.futureacademy.controller;
import com.futureacademy.domain.entity.*; import com.futureacademy.domain.service.*; import com.futureacademy.dto.EntregaRequest;
import jakarta.validation.Valid; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*; import java.util.List;
/** Endpoints de tareas. */
@RestController @RequestMapping("/api/tareas")
public class TareaController {
  private final TareaService tareaService; private final EntregaService entregaService; private final UsuarioService usuarioService;
  public TareaController(TareaService t, EntregaService e, UsuarioService u){this.tareaService=t;this.entregaService=e;this.usuarioService=u;}
  @GetMapping("/pendientes") public List<Tarea> pendientes(Authentication auth){
    Usuario u = usuarioService.findByEmail(auth.getName()).orElseThrow();
    Estudiante e = usuarioService.findEstudianteByUsuario(u).orElseThrow(); return tareaService.tareasPendientes(e);
  }
  @GetMapping("/{id}") public Tarea detalle(@PathVariable Long id){return tareaService.findById(id).orElseThrow();}
  @PostMapping("/entregar") public void entregar(Authentication auth, @RequestBody @Valid EntregaRequest req){
    Usuario u = usuarioService.findByEmail(auth.getName()).orElseThrow();
    Estudiante e = usuarioService.findEstudianteByUsuario(u).orElseThrow();
    if (!req.enlace.startsWith("http://") && !req.enlace.startsWith("https://"))
      throw new RuntimeException("El enlace debe iniciar con http:// o https://");
    entregaService.entregar(e, req.tareaId, req.enlace);
  }

  @GetMapping("/mias")
  public java.util.List<com.futureacademy.dto.EntregaResumen> mias(org.springframework.security.core.Authentication auth){
    Usuario u = usuarioService.findByEmail(auth.getName()).orElseThrow();
    Estudiante e = usuarioService.findEstudianteByUsuario(u).orElseThrow();
    java.util.List<Entrega> entregas = entregaService.listarPorEstudiante(e);
    java.util.List<com.futureacademy.dto.EntregaResumen> res = new java.util.ArrayList<>();
    for (Entrega en : entregas){
      com.futureacademy.dto.EntregaResumen r = new com.futureacademy.dto.EntregaResumen();
      r.entregaId = en.getId();
      r.tareaId = en.getTarea().getId();
      r.tituloTarea = en.getTarea().getTitulo();
      r.clase = en.getTarea().getClase().getNombre();
      r.enlace = en.getEnlace();
      r.entregadaEn = String.valueOf(en.getEntregadaEn());
      r.nota = en.getNota();
      r.observaciones = en.getObservaciones();
      res.add(r);
    }
    return res;
  }
}
