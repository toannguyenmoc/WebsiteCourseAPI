package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.model.Course;

@Service
public interface CourseService {
    
    public Course create(Course course);                   
    public List<Course> findAll();                            
    public Optional<Course> update(Course course);
    public Optional<Course> findById(Integer id);              
    public void deleteById(Integer id); 
}
