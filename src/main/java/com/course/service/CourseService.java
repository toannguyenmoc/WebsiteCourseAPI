package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.model.Course;

@Service
public interface CourseService {
    
    public Course create(Course course);                   
    public List<Course> findAll();                            
    public Course update(Course course);
    public Course findById(Integer id);              
    public Course deleteById(Integer id); 
}
