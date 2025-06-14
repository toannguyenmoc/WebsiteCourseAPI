package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
