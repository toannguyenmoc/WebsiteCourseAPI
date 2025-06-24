package com.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findByCourseId(Integer courseId);
}
