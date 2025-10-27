package com.futureacademy.domain.entity;
import jakarta.persistence.*;
/** Inscripción de un estudiante a una clase (única por estudiante+clase). */
@Entity @Table(name="inscripciones", uniqueConstraints=@UniqueConstraint(columnNames={"estudiante_id","clase_id"}))
public class Inscripcion {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) @JoinColumn(name="estudiante_id") private Estudiante estudiante;
  @ManyToOne(optional=false) @JoinColumn(name="clase_id") private Clase clase;
  public Inscripcion(){} public Inscripcion(Estudiante e, Clase c){this.estudiante=e;this.clase=c;}
  public Long getId(){return id;}
  public Estudiante getEstudiante(){return estudiante;} public void setEstudiante(Estudiante v){this.estudiante=v;}
  public Clase getClase(){return clase;} public void setClase(Clase v){this.clase=v;}
}
