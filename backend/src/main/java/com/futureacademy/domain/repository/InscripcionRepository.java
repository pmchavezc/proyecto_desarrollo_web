package com.futureacademy.domain.repository;
import com.futureacademy.domain.entity.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
  List<Inscripcion> findByEstudiante(Estudiante estudiante);
  Optional<Inscripcion> findByEstudianteAndClase(Estudiante estudiante, Clase clase);
}
