package com.course.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    Page<Lesson> findByCourseId(Integer courseId, Pageable pageable);

}
