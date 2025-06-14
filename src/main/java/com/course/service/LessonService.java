package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.model.Lesson;

@Service
public interface LessonService {
    
    public Lesson create(Lesson lesson);                   
    public List<Lesson> findAll();                            
    public Lesson update(Lesson lesson);
    public Lesson findById(Integer id);              
    public Lesson deleteById(Integer id); 
}
