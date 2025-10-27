package com.futureacademy.domain.entity;
import jakarta.persistence.*; import java.time.LocalDate;
/** Tarea asociada a una clase, con fecha de entrega opcional. */
@Entity @Table(name="tareas")
public class Tarea {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) @JoinColumn(name="clase_id") private Clase clase;
  @Column(nullable=false,length=200) private String titulo;
  @Column(nullable=false,length=1000) private String descripcion;
  private LocalDate fechaEntrega;
  public Tarea(){} public Tarea(Clase c,String t,String d,LocalDate f){this.clase=c;this.titulo=t;this.descripcion=d;this.fechaEntrega=f;}
  public Long getId(){return id;}
  public Clase getClase(){return clase;} public void setClase(Clase v){this.clase=v;}
  public String getTitulo(){return titulo;} public void setTitulo(String v){this.titulo=v;}
  public String getDescripcion(){return descripcion;} public void setDescripcion(String v){this.descripcion=v;}
  public LocalDate getFechaEntrega(){return fechaEntrega;} public void setFechaEntrega(LocalDate v){this.fechaEntrega=v;}
}
