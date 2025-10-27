package com.futureacademy.domain.entity;
import jakarta.persistence.*;
/** Estudiante asociado 1:1 con Usuario. */
@Entity @Table(name="estudiantes")
public class Estudiante {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @OneToOne(optional=false) @JoinColumn(name="usuario_id") private Usuario usuario;
  @Column(nullable=false, length=120) private String nombres;
  @Column(nullable=false, length=120) private String apellidos;
  public Estudiante(){} public Estudiante(Usuario u,String n,String a){this.usuario=u;this.nombres=n;this.apellidos=a;}
  public Long getId(){return id;}
  public Usuario getUsuario(){return usuario;} public void setUsuario(Usuario u){this.usuario=u;}
  public String getNombres(){return nombres;} public void setNombres(String v){this.nombres=v;}
  public String getApellidos(){return apellidos;} public void setApellidos(String v){this.apellidos=v;}
}
