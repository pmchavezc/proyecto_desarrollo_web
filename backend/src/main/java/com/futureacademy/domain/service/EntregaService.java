package com.futureacademy.domain.service;

import com.futureacademy.domain.entity.Entrega;
import com.futureacademy.domain.entity.Estudiante;
import com.futureacademy.domain.entity.Tarea;
import com.futureacademy.domain.repository.EntregaRepository;
import com.futureacademy.domain.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/** Servicio de entrega de tareas. */
@Service
public class EntregaService {

  private final EntregaRepository entregaRepository;
  private final TareaRepository tareaRepository;

  public EntregaService(EntregaRepository entregaRepository, TareaRepository tareaRepository) {
    this.entregaRepository = entregaRepository;
    this.tareaRepository = tareaRepository;
  }

  /** Registra una entrega de tarea. Valida duplicado por estudiante+tarea. */
  public void entregar(Estudiante est, Long tareaId, String enlace) {
    Tarea t = tareaRepository.findById(tareaId)
            .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

    entregaRepository.findByEstudianteAndTarea(est, t)
            .ifPresent(x -> { throw new RuntimeException("Ya has entregado esta tarea"); });

    Entrega e = new Entrega(est, t, enlace, LocalDateTime.now());
    entregaRepository.save(e);
  }

  /** Lista todas las entregas del estudiante (para 'Mis tareas'). */
  public List<Entrega> listarPorEstudiante(Estudiante e) {
    return entregaRepository.findByEstudiante(e);
  }
}