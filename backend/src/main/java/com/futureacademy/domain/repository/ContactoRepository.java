package com.futureacademy.domain.repository;
import com.futureacademy.domain.entity.Contacto; import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactoRepository extends JpaRepository<Contacto, Long> { }
