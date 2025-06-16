package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.CourseType;

public interface CourseTypeRepository extends JpaRepository<CourseType, Integer> {
	
}
