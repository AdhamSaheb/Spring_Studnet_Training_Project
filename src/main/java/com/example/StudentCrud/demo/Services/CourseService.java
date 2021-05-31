package com.example.StudentCrud.demo.Services;

import com.example.StudentCrud.demo.Models.Course;
import com.example.StudentCrud.demo.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository repository ;

    public List<Course> getCourses() {
        return repository.findAll();
    }

    public List<Course> getAllCoursesByName(String courseName){
        return repository.findAllByName(courseName);
    }

    public Course getCourseById(Long id ) {
        return repository.findById(id).get();
    }

    public Course addCourse(Course course) {
        return repository.save(course);
    }

    public Course updateCourse(Long id,Course course){
        return repository.findById(id)
                .map(newCourse -> {
                    newCourse.setName(course.getName());
                    newCourse.setMaximumParticipants(course.getMaximumParticipants());
                    newCourse.setEnrolledStudents(course.getEnrolledStudents());
                    return repository.save(newCourse);
                })
                .orElseGet(() -> {
                    course.setId(id);
                    return repository.save(course);
                });
    }

    public void deleteCourseById(Long id) {
        repository.deleteById(id);
    }

    //Todo : create add student to course 

}
