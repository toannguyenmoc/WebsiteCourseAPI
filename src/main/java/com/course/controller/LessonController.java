package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.service.LessonService;
import jakarta.validation.Valid;

import com.course.dto.LessonRequestDTO;
import com.course.dto.LessonResponseDTO;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired private LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<LessonResponseDTO>> getAllLessons() {
        return ResponseEntity.ok(lessonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> getLessonById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(lessonService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LessonResponseDTO> createLesson(@Valid @RequestBody LessonRequestDTO dto) {
        return ResponseEntity.ok(lessonService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> updateLesson(@PathVariable("id") Integer id, @Valid @RequestBody LessonRequestDTO dto) {
        return ResponseEntity.ok(lessonService.update(id, dto)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> deleteLesson(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(lessonService.deleteById(id));
    }

}
