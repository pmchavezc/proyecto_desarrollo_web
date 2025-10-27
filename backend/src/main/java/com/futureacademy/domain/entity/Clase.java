package com.futureacademy.domain.entity;
import jakarta.persistence.*;
/** Clase académica con maestro y estado 'activa'. */
@Entity @Table(name="clases")
public class Clase {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false,length=120) private String nombre;
  @ManyToOne(optional=false) @JoinColumn(name="maestro_id") private Maestro maestro;
  @Column(nullable=false) private boolean activa=true;
  public Clase(){} public Clase(String nombre, Maestro maestro, boolean activa){this.nombre=nombre;this.maestro=maestro;this.activa=activa;}
  public Long getId(){return id;}
  public String getNombre(){return nombre;} public void setNombre(String v){this.nombre=v;}
  public Maestro getMaestro(){return maestro;} public void setMaestro(Maestro v){this.maestro=v;}
  public boolean isActiva(){return activa;} public void setActiva(boolean v){this.activa=v;}
}
