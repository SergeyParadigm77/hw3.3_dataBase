package ru.hogwarts.school.service;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {
    HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    @PostConstruct
    public void initQuestions() {
        students.put(1L, new Student(1L, "Sting", 19));
        students.put(2L, new Student(2L, "Sam", 20));
        students.put(3L, new Student(3L, "Bob", 21));
        students.put(4L, new Student(4L, "Ron", 22));
        students.put(5L, new Student(5L, "Gor", 19));
    }

    public Student createStudent (Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }
    public Student findStudentById(Long id) {

        return students.get(id);
    }
    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }
    public Student deleteStudent(Long id) {
        students.remove(id);
        return students.get(id);
    }
    public Collection<Student> getAll() {
        return students.values();
    }
    public Collection<Student> getAllByAge(int age) {
        return getAll().stream().filter(it -> it.getAge() == age).collect(Collectors.toList());
    }
}