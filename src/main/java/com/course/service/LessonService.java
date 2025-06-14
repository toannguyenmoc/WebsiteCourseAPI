package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.model.Lesson;

@Service
public interface LessonService {
    
    public Lesson create(Lesson lesson);                   
    public List<Lesson> findAll();                            
    public Optional<Lesson> update(Lesson lesson);
    public Optional<Lesson> findById(Integer id);              
    public void deleteById(Integer id); 
}
