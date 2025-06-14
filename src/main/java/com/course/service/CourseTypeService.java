package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.model.CourseType;

@Service
public interface CourseTypeService {
    
    public CourseType create(CourseType courseType);                   
    public List<CourseType> findAll();                            
    public Optional<CourseType> update(CourseType courseType);
    public Optional<CourseType> findById(Integer id);              
    public void deleteById(Integer id); 
}
