package com.futureacademy.domain.service;
import com.futureacademy.domain.entity.Contacto; import com.futureacademy.domain.repository.ContactoRepository;
import org.springframework.stereotype.Service; import java.time.LocalDateTime;
/** Servicio para guardar contactos. */
@Service public class ContactoService {
  private final ContactoRepository contactoRepository; public ContactoService(ContactoRepository c){this.contactoRepository=c;}
  public void guardar(String nombre, String email, String telefono, String mensaje){
    contactoRepository.save(new Contacto(nombre,email,telefono,mensaje, LocalDateTime.now()));
  }
}
