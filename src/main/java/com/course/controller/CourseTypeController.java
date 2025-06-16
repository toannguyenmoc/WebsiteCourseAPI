package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.dto.CourseTypeRequestDTO;
import com.course.dto.CourseTypeResponseDTO;

import com.course.service.CourseTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course-type")
public class CourseTypeController {

	@Autowired
	private CourseTypeService courseTypeService;

	@GetMapping
	public ResponseEntity<List<CourseTypeResponseDTO>> getAllCourses() {
		return ResponseEntity.ok(courseTypeService.findAll());
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
	public ResponseEntity<CourseTypeResponseDTO> updateCourse(@PathVariable("id") Integer id,
			@Valid @RequestBody CourseTypeRequestDTO dto) {
		return ResponseEntity.ok(courseTypeService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CourseTypeResponseDTO> deleteCourse(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(courseTypeService.deleteById(id));
	}
}
