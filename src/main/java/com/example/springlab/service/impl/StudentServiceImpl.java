package com.example.springlab.service.impl;

import com.example.springlab.domain.Student;
import com.example.springlab.dto.StudentCreateRequest;
import com.example.springlab.dto.StudentResponse;
import com.example.springlab.repository.StudentRepository;
import com.example.springlab.service.StudentService;
import com.example.springlab.web.advice.ConflictException;
import com.example.springlab.web.advice.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.naming.ConfigurationException;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    //inyeccion de dependencia
    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo){
        this.repo = repo;
    }

    @Override
    public StudentResponse create(StudentCreateRequest request) {
        //aqui sabemos si dentro de base de datos tenemos el correo
        if(repo.existsByEmail(request.getEmail())){
            throw new ConflictException("El email ya esta registrado");
        }
        Student s = new Student();
        s.setFullName(request.getFullName());
        s.setEmail(request.getEmail());
        s.setBirthDate(request.getBirthDate());
        s.setActive(true);

        Student saved = repo.save(s);
        return toResponse(saved);
    }

    @Override
    public StudentResponse getById(Long id) {
        Student s = repo.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        return toResponse(s);
    }

    @Override
    public StudentResponse deactivate(Long id) {
        Student s = repo.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        s.setActive(false);
        return toResponse(repo.save(s));
    }

    //Mapeo interno entidad -> DTO de salida
    private StudentResponse toResponse(Student s){
        StudentResponse r = new StudentResponse();
        r.setId(s.getId());
        r.setFullName(s.getFullName());
        r.setBirthDate(s.getBirthDate());
        r.setActive(s.getActive());
        r.setEmail(s.getEmail());
        return r;
    }

    public Page<StudentResponse> listAll(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students;

        if (name != null && !name.isEmpty()) {
            students = repo.findByFullNameContainingIgnoreCase(name, pageable);
        } else {
            students = repo.findAll(pageable);
        }
        return students.map(this::toResponse);
    }
}