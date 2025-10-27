package com.futureacademy.domain.service;
import com.futureacademy.domain.entity.Estudiante; import com.futureacademy.domain.entity.Usuario;
import com.futureacademy.domain.repository.EstudianteRepository; import com.futureacademy.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service; import java.util.Optional;
/** Servicio para operaciones de Usuario/Estudiante. */
@Service public class UsuarioService {
  private final UsuarioRepository usuarioRepository; private final EstudianteRepository estudianteRepository;
  public UsuarioService(UsuarioRepository u, EstudianteRepository e){this.usuarioRepository=u;this.estudianteRepository=e;}
  public Optional<Usuario> findByEmail(String email){return usuarioRepository.findByEmail(email);}
  public Optional<Estudiante> findEstudianteByUsuario(Usuario u){return estudianteRepository.findByUsuario(u);}
}
