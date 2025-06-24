package com.course.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dto.CommentRequestDTO;
import com.course.dto.CommentResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.CommentMapper;
import com.course.model.Comment;
import com.course.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentResponseDTO create(CommentRequestDTO dto) {
        Comment comment = CommentMapper.toEntity(dto);
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.toResponse(savedComment);
    }

    @Override
    public List<CommentResponseDTO> findAll() {
        List<Comment> comments = commentRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Comment::getCommentedDate).reversed())
                .collect(Collectors.toList());
        return CommentMapper.toResponseDTOList(comments);
    }

    @Override
    public CommentResponseDTO update(Integer id, CommentRequestDTO dto) {
        findById(id);
        Comment comment = CommentMapper.toEntity(dto);
        comment.setId(id);
        Comment updatedComment = commentRepository.save(comment);
        return CommentMapper.toResponse(updatedComment);
    }

    @Override
    public CommentResponseDTO findById(Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bình luận với ID: " + id));
        return CommentMapper.toResponse(comment);
    }

    @Override
    public CommentResponseDTO deleteById(Integer id) {
        CommentResponseDTO comment = findById(id);
        commentRepository.deleteById(id);
        return comment;
    }

    @Override
    public List<CommentResponseDTO> findCourseId(Integer courseId) {
        List<Comment> comments = commentRepository.findByCourseId(courseId)
                .stream()
                .sorted(Comparator.comparing(Comment::getCommentedDate).reversed())
                .collect(Collectors.toList());
        List<CommentResponseDTO> dtos = comments.stream().map(CommentMapper::toResponse).toList();
        return dtos;
    }
}
