package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    Collection<Student> getAll() {
        return studentService.getAll();
    }
    @GetMapping("/age")
    Collection<Student> getAllByAge(@RequestParam int age) {
        return studentService.getAllByAge(age);
    }
    @GetMapping("/{id}")
    public Student getStudentInfo(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        studentService.createStudent(student);
        return student;
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }
    @DeleteMapping({"/id"})
    public Student deleteStudent(@RequestParam Long id) {
        return studentService.deleteStudent(id);
    }
}
