package com.futureacademy.domain.service;
import com.futureacademy.domain.entity.*; import com.futureacademy.domain.repository.*; import org.springframework.stereotype.Service;
import java.util.*; import java.util.stream.Collectors;
/** Servicio de clases e inscripciones. */
@Service public class ClaseService {
  private final ClaseRepository claseRepository; private final InscripcionRepository inscripcionRepository;
  public ClaseService(ClaseRepository c, InscripcionRepository i){this.claseRepository=c;this.inscripcionRepository=i;}

  public List<Clase> clasesActivas(){return claseRepository.findByActivaTrue();}

  public List<Clase> clasesInscritas(Estudiante e){
    return inscripcionRepository.findByEstudiante(e).stream().map(Inscripcion::getClase).collect(Collectors.toList());
  }

  public List<Clase> clasesNoInscritasActivas(Estudiante e){
    List<Clase> activas = claseRepository.findByActivaTrue();
    Set<Long> inscIds = inscripcionRepository.findByEstudiante(e).stream().map(i->i.getClase().getId()).collect(Collectors.toSet());
    List<Clase> r = new ArrayList<>(); for (Clase c: activas) if(!inscIds.contains(c.getId())) r.add(c); return r;
  }

  public void inscribir(Estudiante e, Long claseId){
    Clase c = claseRepository.findById(claseId).orElseThrow(() -> new RuntimeException("Clase no encontrada"));
    inscripcionRepository.findByEstudianteAndClase(e,c).ifPresent(x->{throw new RuntimeException("Ya estás inscrito en esta clase");});
    inscripcionRepository.save(new Inscripcion(e,c));
  }
}
