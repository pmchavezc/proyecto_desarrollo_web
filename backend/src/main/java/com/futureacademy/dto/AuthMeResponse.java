package com.futureacademy.dto;
/** Respuesta de /api/auth/me (datos básicos del usuario y estudiante). */
public class AuthMeResponse {
  public Long usuarioId; public String email; public String rol;
  public Long estudianteId; public String nombres; public String apellidos;
}