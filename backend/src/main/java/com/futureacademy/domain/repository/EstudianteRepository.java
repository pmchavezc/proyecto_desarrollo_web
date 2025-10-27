package com.futureacademy.domain.repository;
import com.futureacademy.domain.entity.Estudiante; import com.futureacademy.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> { Optional<Estudiante> findByUsuario(Usuario usuario); }
