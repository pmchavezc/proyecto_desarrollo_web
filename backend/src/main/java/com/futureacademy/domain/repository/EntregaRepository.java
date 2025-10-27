package com.futureacademy.domain.repository;
import com.futureacademy.domain.entity.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
  Optional<Entrega> findByEstudianteAndTarea(Estudiante estudiante, Tarea tarea);
  List<Entrega> findByEstudiante(Estudiante estudiante);
}
