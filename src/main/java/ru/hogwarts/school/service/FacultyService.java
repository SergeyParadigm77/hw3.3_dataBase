package ru.hogwarts.school.service;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    @PostConstruct
    public void initQuestions() {
        faculties.put(1L, new Faculty(1L, "Sting", "Red"));
        faculties.put(2L, new Faculty(2L, "Sam", "Black"));
        faculties.put(3L, new Faculty(3L, "Bob", "Yellow"));
        faculties.put(4L, new Faculty(4L, "Ron", "Blue"));
        faculties.put(5L, new Faculty(5L, "Gor", "Grey"));
    }
    public Faculty createFaculty (Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFacultyById(Long id) {
        return faculties.get(id);
    }
    public Faculty editFaculty(Faculty faculty) {
        if(faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }
    public Faculty deleteFaculty(Long id) {
        faculties.remove(id);
        return faculties.get(id);
    }
    public Collection<Faculty> getAll() {
        return faculties.values();
    }
    public Collection<Faculty> getAllByColor(String color) {
        return faculties.values().stream().filter(it -> it.getColor().equals(color)).collect(Collectors.toList());
    }
}
