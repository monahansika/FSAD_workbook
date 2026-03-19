package com.klu.service;

import com.klu.entity.Course;
import com.klu.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    // CREATE
    public Course addCourse(Course course) {
        return repo.save(course);
    }

    // READ ALL
    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    // READ BY ID
    public Optional<Course> getCourseById(Long id) {
        return repo.findById(id);
    }

    // UPDATE
    public Course updateCourse(Long id, Course course) {
        course.setCourseId(id);
        return repo.save(course);
    }

    // DELETE
    public void deleteCourse(Long id) {
        repo.deleteById(id);
    }

    // SEARCH
    public List<Course> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }
}