package com.futureacademy.domain.service;
import com.futureacademy.domain.entity.*; import com.futureacademy.domain.repository.*; import org.springframework.stereotype.Service;
import java.util.*; import java.util.stream.Collectors;
/** Servicio de tareas pendientes. */
@Service public class TareaService {
  private final TareaRepository tareaRepository; private final InscripcionRepository inscripcionRepository; private final EntregaRepository entregaRepository;
  public TareaService(TareaRepository t, InscripcionRepository i, EntregaRepository e){this.tareaRepository=t;this.inscripcionRepository=i;this.entregaRepository=e;}

  public List<Tarea> tareasPendientes(Estudiante e){
    List<Clase> clases = inscripcionRepository.findByEstudiante(e).stream().map(Inscripcion::getClase).collect(Collectors.toList());
    if (clases.isEmpty()) return Collections.emptyList();
    List<Tarea> todas = tareaRepository.findByClaseIn(clases);
    Set<Long> entregadas = entregaRepository.findByEstudiante(e).stream().map(x->x.getTarea().getId()).collect(Collectors.toSet());
    List<Tarea> r = new ArrayList<>(); for (Tarea t: todas) if(!entregadas.contains(t.getId())) r.add(t); return r;
  }

  public Optional<Tarea> findById(Long id){return tareaRepository.findById(id);}
}
