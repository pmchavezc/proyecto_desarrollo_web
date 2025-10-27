package com.futureacademy.domain.repository;
import com.futureacademy.domain.entity.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface TareaRepository extends JpaRepository<Tarea, Long> { List<Tarea> findByClaseIn(List<Clase> clases); }
