package com.course.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.LessonRequestDTO;
import com.course.dto.LessonResponseDTO;

@Service
public interface LessonService {
    
	public LessonResponseDTO create(LessonRequestDTO lessonRequestDTO);                   
    public List<LessonResponseDTO> findAll();                            
    public LessonResponseDTO update(Integer id, LessonRequestDTO lessonRequestDTO);
    public LessonResponseDTO findById(Integer id);              
    public LessonResponseDTO deleteById(Integer id); 
    public ResponseEntity<?> getPagedLessons(Integer courseId, int page, int size);
    public List<LessonResponseDTO> getPagedLessonss(int page, int size, Integer userId);
}
