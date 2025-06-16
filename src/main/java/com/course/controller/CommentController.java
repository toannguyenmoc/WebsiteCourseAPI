package com.course.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.dto.CommentRequestDTO;
import com.course.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping
    public ResponseEntity<?> getAllComments() {
        return ResponseEntity.ok().body(commentService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(commentService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentRequestDTO dto) {
        return ResponseEntity.ok().body(commentService.create(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id, @Valid @RequestBody CommentRequestDTO dto) {
        return ResponseEntity.ok().body(commentService.update(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id) {
        return ResponseEntity.ok().body(commentService.deleteById(id));
    }

}
