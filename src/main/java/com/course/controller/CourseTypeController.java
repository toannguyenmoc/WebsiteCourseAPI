package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.CourseTypeRequestDTO;
import com.course.dto.CourseTypeResponseDTO;
import com.course.service.CourseTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course-type")
public class CourseTypeController {

	 @Autowired private CourseTypeService courseTypeService;
//
//	    @GetMapping
//	    public ResponseEntity<List<CourseTypeResponseDTO>> getAllCourses() {
//	        return ResponseEntity.ok(courseTypeService.findAll());
//	    }
	 @GetMapping
	    public ResponseEntity<?> getPagedCourses(
	            @RequestParam(name = "page", defaultValue = "0") int page,
	            @RequestParam(name = "size", defaultValue = "10") int size) {
	        return courseTypeService.getPagedCourseTypes(page, size);
	    }
	    @GetMapping("/{id}")
	    public ResponseEntity<CourseTypeResponseDTO> getCourseTypeById(@PathVariable("id") Integer id) {
	        return ResponseEntity.ok(courseTypeService.findById(id));
	    }
	    @PostMapping
	    public ResponseEntity<CourseTypeResponseDTO> createCourseType(@Valid @RequestBody CourseTypeRequestDTO dto) {
	    	return ResponseEntity.ok(courseTypeService.create(dto));
	    }
	    @PutMapping("/{id}")
	    public ResponseEntity<CourseTypeResponseDTO> updateCourse(@PathVariable("id") Integer id, @Valid @RequestBody CourseTypeRequestDTO dto) {
	    	return ResponseEntity.ok(courseTypeService.update(id, dto)); 
	    }
	    @DeleteMapping("/{id}")
	    public ResponseEntity<CourseTypeResponseDTO> deleteCourse(@PathVariable("id") Integer id) {
	        return ResponseEntity.ok(courseTypeService.deleteById(id));
	    }
}
