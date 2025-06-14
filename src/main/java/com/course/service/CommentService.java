package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.model.Comment;

@Service
public interface CommentService {
    
    public Comment create(Comment comment);                   
    public List<Comment> findAll();                            
    public Comment update(Comment comment);
    public Comment findById(Integer id);              
    public Comment deleteById(Integer id); 
}
