package com.course.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.course.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    Page<Lesson> findByCourseId(Integer courseId, Pageable pageable);
    
    @Query(value = "SELECT l.* FROM lessons l " +
            "JOIN courses c ON l.course_id = c.id " +
            "JOIN accounts a ON c.account_id = a.id " +
            "WHERE a.id = ?1 AND a.role = 2",
        countQuery = "SELECT COUNT(*) FROM lessons l " +
            "JOIN courses c ON l.course_id = c.id " +
            "JOIN accounts a ON c.account_id = a.id " +
            "WHERE a.id = ?1 AND a.role = 2",
        nativeQuery = true)
    Page<Lesson> findLessonsByAccountIdAndRole2Native(Integer userId, Pageable pageable);





}
