package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	

}
