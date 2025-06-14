package com.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.exception.ResourceNotFoundException;
import com.course.model.Comment;
import com.course.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment update(Comment comment) {
    	findById(comment.getId());
        return commentRepository.save(comment);
    }

    @Override
    public Comment findById(Integer id) {
    	return commentRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy"));
    }

    @Override
    public Comment deleteById(Integer id) {
    	Comment comment = findById(id);
    	commentRepository.deleteById(id);
        return comment;
    }
}
