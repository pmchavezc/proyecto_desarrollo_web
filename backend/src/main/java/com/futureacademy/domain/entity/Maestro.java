package com.futureacademy.domain.entity;
import jakarta.persistence.*;
/** Maestro responsable de clases. */
@Entity @Table(name="maestros")
public class Maestro {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false,length=120) private String nombres;
  @Column(nullable=false,length=120) private String apellidos;
  public Maestro(){} public Maestro(String n,String a){this.nombres=n;this.apellidos=a;}
  public Long getId(){return id;}
  public String getNombres(){return nombres;} public void setNombres(String v){this.nombres=v;}
  public String getApellidos(){return apellidos;} public void setApellidos(String v){this.apellidos=v;}
}
