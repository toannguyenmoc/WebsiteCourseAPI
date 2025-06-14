package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.model.Comment;

@Service
public interface CommentService {
    
    public Comment create(Comment comment);                   
    public List<Comment> findAll();                            
    public Optional<Comment> update(Comment comment);
    public Optional<Comment> findById(Integer id);              
    public void deleteById(Integer id); 
}
