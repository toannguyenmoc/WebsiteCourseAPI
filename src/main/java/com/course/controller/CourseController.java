package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.service.CourseService;
import jakarta.validation.Valid;

import com.course.dto.CourseRequestDTO;
import com.course.dto.CourseResponseDTO;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO dto) {
    	return ResponseEntity.ok(courseService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable("id") Integer id, @Valid @RequestBody CourseRequestDTO dto) {
    	return ResponseEntity.ok(courseService.update(id, dto)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> deleteCourse(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.deleteById(id));
    }

}

