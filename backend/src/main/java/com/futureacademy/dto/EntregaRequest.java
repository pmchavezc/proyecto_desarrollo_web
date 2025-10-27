package com.futureacademy.dto;
import jakarta.validation.constraints.NotBlank; import jakarta.validation.constraints.NotNull;
/** Petición para entregar una tarea (enlace http/https). */
public class EntregaRequest { @NotNull public Long tareaId; @NotBlank public String enlace; }