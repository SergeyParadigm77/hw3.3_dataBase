package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FacultyServiceTest {
    private final FacultyService facultyService = new FacultyService();

    @Test
    public void shouldCreateFaculty() {
        //Given
        Faculty faculty = new Faculty(1L, "Gryffindor", "White");
        //when
        Faculty createdFaculty = facultyService.createFaculty(new Faculty(faculty.getId(), faculty.getName(), faculty.getColor()));
        //then
        Assertions.assertEquals(faculty, createdFaculty);

    }

    @Test
    public void shouldFindFacultyById() {
        //Given
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "White");
        Faculty faculty2 = new Faculty(2L, "Slytherin", "Yellow");
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        //when
        Faculty foundedFaculty = facultyService.findFacultyById(2L);
        //then
        Assertions.assertEquals(faculty2, foundedFaculty);
    }

    @Test
    public void shouldEditFaculty() {
        //Given
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "White");
        Faculty faculty2 = new Faculty(1L, "Slytherin", "Yellow");
        facultyService.createFaculty(faculty1);
        //when
        facultyService.editFaculty(faculty2);
        Faculty editedFaculty = facultyService.findFacultyById(1L);
        //then
        Assertions.assertEquals(faculty2, editedFaculty);
    }

    @Test
    public void shouldReturnNullWhenNoFacultyToEdit() {
        //Given
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "White");
        Faculty faculty2 = new Faculty(2L, "Slytherin", "Yellow");
        facultyService.createFaculty(faculty1);
        //when//then
        Assertions.assertNull(facultyService.editFaculty(faculty2));
    }

    @Test
    public void shouldDeleteFacultyById() {
        //Given
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "White");
        Faculty faculty2 = new Faculty(2L, "Slytherin", "Yellow");
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);

        Map<Long, Faculty> testFaculties = new HashMap<>();
        testFaculties.put(2L, faculty2);
        //when
        facultyService.deleteFaculty(1L);
        //then
        Assertions.assertEquals(testFaculties, facultyService.faculties);
    }

    @Test
    public void shouldReturnAllFaculties() {
        //given
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "White");
        Faculty faculty2 = new Faculty(2L, "Slytherin", "Yellow");
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);

        Collection<Faculty> testFacultiesList = new ArrayList<>();
        testFacultiesList.add(faculty1);
        testFacultiesList.add(faculty2);
        //when
        Collection<Faculty> facultiesList = new ArrayList<>(facultyService.getAll());
        // then
        Assertions.assertEquals(facultiesList, testFacultiesList);
    }
    @Test
    public void shouldReturnAllByColor() {
        //given
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "White");
        Faculty faculty2 = new Faculty(2L, "Slytherin", "Yellow");
        Faculty faculty3 = new Faculty(3L, "Ravenclaw", "White");
        facultyService.createFaculty(faculty1);
        facultyService.createFaculty(faculty2);
        facultyService.createFaculty(faculty3);

        Collection<Faculty> testFacultiesList = new ArrayList<>();
        testFacultiesList.add(faculty1);
        testFacultiesList.add(faculty3);

        //when
        Collection<Faculty> facultiesList = new ArrayList<>(facultyService.getAllByColor("White"));

        //then
        Assertions.assertEquals(testFacultiesList, facultiesList);
    }
}
