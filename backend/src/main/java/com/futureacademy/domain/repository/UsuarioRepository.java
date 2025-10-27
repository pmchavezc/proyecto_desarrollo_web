package com.futureacademy.domain.repository;
import com.futureacademy.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
/** Acceso a usuarios por email. */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { Optional<Usuario> findByEmail(String email); }
