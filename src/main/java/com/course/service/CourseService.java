package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.dto.CourseRequestDTO;
import com.course.dto.CourseResponseDTO;

@Service
public interface CourseService {
    
    public CourseResponseDTO create(CourseRequestDTO courseRequestDTO);                   
    public List<CourseResponseDTO> findAll();                            
    public CourseResponseDTO update(Integer id, CourseRequestDTO courseRequestDTO);
    public CourseResponseDTO findById(Integer id);              
    public CourseResponseDTO deleteById(Integer id); 
}
