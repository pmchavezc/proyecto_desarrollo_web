package com.futureacademy.domain.entity;
import jakarta.persistence.*; import java.time.LocalDateTime;
/** Entrega de tarea por estudiante, almacena enlace y fecha/hora. */
@Entity @Table(name="entregas", uniqueConstraints=@UniqueConstraint(columnNames={"estudiante_id","tarea_id"}))
public class Entrega {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) @JoinColumn(name="estudiante_id") private Estudiante estudiante;
  @ManyToOne(optional=false) @JoinColumn(name="tarea_id") private Tarea tarea;
  @Column(nullable=false,length=1000) private String enlace;
  private LocalDateTime entregadaEn;
  private Integer nota; // Nota (0-100) opcional
  private String observaciones; // Comentarios del maestro
  public Entrega(){} public Entrega(Estudiante e,Tarea t,String l,LocalDateTime d){this.estudiante=e;this.tarea=t;this.enlace=l;this.entregadaEn=d;}
  public Long getId(){return id;}
  public Estudiante getEstudiante(){return estudiante;} public void setEstudiante(Estudiante v){this.estudiante=v;}
  public Tarea getTarea(){return tarea;} public void setTarea(Tarea v){this.tarea=v;}
  public String getEnlace(){return enlace;} public void setEnlace(String v){this.enlace=v;}
  public LocalDateTime getEntregadaEn(){return entregadaEn;} public void setEntregadaEn(LocalDateTime v){this.entregadaEn=v;}

  public Integer getNota(){return nota;} public void setNota(Integer n){this.nota=n;}
  public String getObservaciones(){return observaciones;} public void setObservaciones(String o){this.observaciones=o;}
}
