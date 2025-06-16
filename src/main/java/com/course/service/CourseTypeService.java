package com.course.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.course.dto.CourseTypeRequestDTO;
import com.course.dto.CourseTypeResponseDTO;


@Service
public interface CourseTypeService {
    
    public CourseTypeResponseDTO create(CourseTypeRequestDTO courseType);                   
    public List<CourseTypeResponseDTO> findAll();                            
    public CourseTypeResponseDTO update(Integer id, CourseTypeRequestDTO courseTypeRequestDTO);
    public CourseTypeResponseDTO findById(Integer id);              
    public CourseTypeResponseDTO deleteById(Integer id); 
    public ResponseEntity<?> getPagedCourseTypes(int page, int size);
}
