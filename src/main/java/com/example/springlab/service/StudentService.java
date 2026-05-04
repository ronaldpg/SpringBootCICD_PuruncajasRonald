package com.example.springlab.service;

import com.example.springlab.domain.Student;
import com.example.springlab.dto.StudentCreateRequest;
import com.example.springlab.dto.StudentResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    //Crear un estudiante
    StudentResponse create(StudentCreateRequest request);

    //Buscar estudiante por ID
    StudentResponse getById(Long id);

    //Lista todos los estudiantes
    Page<StudentResponse> listAll(String name, int page, int size);

    //Cambiar el estado
    StudentResponse deactivate(Long id);
}