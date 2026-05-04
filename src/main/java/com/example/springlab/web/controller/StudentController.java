package com.example.springlab.web.controller;

import com.example.springlab.dto.StudentCreateRequest;
import com.example.springlab.dto.StudentResponse;
import com.example.springlab.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    // inyeccion de dependencia
    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    // crear un estudiante
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    // ESTUDIANTE PAGINACION Y BUSQUEDA
    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getAllStudents(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(service.listAll(name, page, size));
    }

    // Con patch se actualiza algo especifico (desactivación lógica)
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<StudentResponse> deactivateStudent(@PathVariable Long id){
        return ResponseEntity.ok(service.deactivate(id));
    }
}
