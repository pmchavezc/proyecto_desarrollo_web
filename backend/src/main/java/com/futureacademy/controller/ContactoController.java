package com.futureacademy.controller;
import com.futureacademy.domain.service.ContactoService; import org.springframework.web.bind.annotation.*; import java.util.Map;
/* Endpoint público para guardar contactos. */
@RestController @RequestMapping("/api/contactos")
public class ContactoController {

  private final ContactoService contactoService;

  public ContactoController(ContactoService s){this.contactoService=s;}
  @PostMapping public Map<String,String> crear(@RequestBody Map<String,String> body){
    String nombre = body.getOrDefault("nombre","").trim();
    String email = body.getOrDefault("email","").trim();
    String telefono = body.getOrDefault("telefono","").trim();
    String mensaje = body.getOrDefault("mensaje","").trim();
    if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || mensaje.isEmpty()) throw new RuntimeException("Todos los campos son obligatorios");
    if (!email.contains("@")) throw new RuntimeException("Email inválido");
    contactoService.guardar(nombre,email,telefono,mensaje);
    return java.util.Map.of("status","ok");
  }
}
