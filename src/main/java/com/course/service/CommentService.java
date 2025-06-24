package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.dto.CommentRequestDTO;
import com.course.dto.CommentResponseDTO;

@Service
public interface CommentService {

    public CommentResponseDTO create(CommentRequestDTO dto);
    public List<CommentResponseDTO> findAll();
    public CommentResponseDTO update(Integer id, CommentRequestDTO dto);
    public CommentResponseDTO findById(Integer id);
    public CommentResponseDTO deleteById(Integer id);
    public List<CommentResponseDTO> findCourseId(Integer courseId);
}
