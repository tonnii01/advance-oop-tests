package com.example.app.controller;

import com.example.app.entity.Student;
import com.example.app.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // GET student by id
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // POST create student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    // PUT update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        return repository.save(student);
    }

    // PATCH update student name/email
    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable Long id, @RequestBody Student student) {
        Student existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        if (student.getName() != null) {
            existing.setName(student.getName());
        }
        if (student.getEmail() != null) {
            existing.setEmail(student.getEmail());
        }
        return repository.save(existing);
    }

    // DELETE student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
