package com.futureacademy.domain.repository;
import com.futureacademy.domain.entity.Clase; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ClaseRepository extends JpaRepository<Clase, Long> { List<Clase> findByActivaTrue(); }
