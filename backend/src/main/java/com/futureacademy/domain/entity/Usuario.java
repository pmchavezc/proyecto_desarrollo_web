package com.futureacademy.domain.entity;
import jakarta.persistence.*;
/** Usuario del sistema (login por email). */
@Entity @Table(name="usuarios")
public class Usuario {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true, length=120) private String email;
  @Column(name="password_hash", nullable=false, length=120) private String passwordHash;
  @Column(nullable=false, length=20) private String rol;
  public Usuario(){}
  public Usuario(String email, String passwordHash, String rol){this.email=email; this.passwordHash=passwordHash; this.rol=rol;}
  public Long getId(){return id;}
  public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
  public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String p){this.passwordHash=p;}
  public String getRol(){return rol;} public void setRol(String r){this.rol=r;}
}
