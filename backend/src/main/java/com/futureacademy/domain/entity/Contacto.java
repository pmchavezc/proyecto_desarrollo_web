package com.futureacademy.domain.entity;
import jakarta.persistence.*; import java.time.LocalDateTime;
/** Registro de contacto enviado desde el formulario público. */
@Entity @Table(name="contactos")
public class Contacto {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false,length=120) private String nombre;
  @Column(nullable=false,length=120) private String email;
  @Column(nullable=false,length=20)  private String telefono;
  @Column(nullable=false,length=1000) private String mensaje;
  private LocalDateTime creadoEn;
  public Contacto(){} public Contacto(String n,String e,String t,String m,LocalDateTime c){this.nombre=n;this.email=e;this.telefono=t;this.mensaje=m;this.creadoEn=c;}
  public Long getId(){return id;}
  public String getNombre(){return nombre;} public void setNombre(String v){this.nombre=v;}
  public String getEmail(){return email;} public void setEmail(String v){this.email=v;}
  public String getTelefono(){return telefono;} public void setTelefono(String v){this.telefono=v;}
  public String getMensaje(){return mensaje;} public void setMensaje(String v){this.mensaje=v;}
  public LocalDateTime getCreadoEn(){return creadoEn;} public void setCreadoEn(LocalDateTime v){this.creadoEn=v;}
}
