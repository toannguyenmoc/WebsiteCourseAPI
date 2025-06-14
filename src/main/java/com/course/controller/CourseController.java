package com.course.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.service.AccountService;
import com.course.service.CommissionService;
import com.course.service.CourseService;
import com.course.service.CourseTypeService;

import jakarta.validation.Valid;

import com.course.dto.CourseRequestDTO;
import com.course.dto.CourseResponseDTO;
import com.course.mapper.CourseMapper;
import com.course.model.Course;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired private CourseService courseService;
    @Autowired private AccountService accountService;
    @Autowired private CourseTypeService courseTypeService;
    @Autowired private CommissionService commissionService;

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> responseList = courseService.findAll().stream()
            .map(CourseMapper::toResponse)
            .sorted(Comparator.comparing(CourseResponseDTO::getTitle).reversed()
            					.thenComparing(CourseResponseDTO::getPrice).reversed()
            					.thenComparing(CourseResponseDTO::getCreatedDate))
            .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(CourseMapper.toResponse(courseService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO dto) {
        Course course = CourseMapper.toEntity(
            dto,
            accountService.findById(dto.getAccountId()),
            courseTypeService.findById(dto.getCourseTypeId()),
            commissionService.findById(dto.getCommissionId())
        );
        Course saved = courseService.create(course);
        return ResponseEntity.ok(CourseMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable("id") Integer id, @Valid @RequestBody CourseRequestDTO dto) {
        
        Course updatedCourse = CourseMapper.toEntity(
            dto,
            accountService.findById(dto.getAccountId()),
            courseTypeService.findById(dto.getCourseTypeId()),
            commissionService.findById(dto.getCommissionId())
        );
        updatedCourse.setId(id);

        return ResponseEntity.ok(CourseMapper.toResponse(courseService.update(updatedCourse)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> deleteCourse(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(CourseMapper.toResponse(courseService.deleteById(id)));
    }

}

