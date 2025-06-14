package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.model.CourseType;

@Service
public interface CourseTypeService {
    
    public CourseType create(CourseType courseType);                   
    public List<CourseType> findAll();                            
    public CourseType update(CourseType courseType);
    public CourseType findById(Integer id);              
    public CourseType deleteById(Integer id); 
}
