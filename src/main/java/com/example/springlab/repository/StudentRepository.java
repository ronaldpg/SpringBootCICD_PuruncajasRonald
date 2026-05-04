package com.example.springlab.repository;

import com.example.springlab.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface StudentRepository  extends JpaRepository<Student, Long> {
    //buscar un estudiante por email
    Optional<Student> findByEmail(String email);

    //Buscar el nombre
    Page<Student> findByFullNameContainingIgnoreCase(String name, org.springframework.data.domain.Pageable pageable);

    //Respusta si existe al menos un registro
    boolean existsByEmail(String email);

}
