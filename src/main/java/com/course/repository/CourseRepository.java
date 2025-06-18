package com.course.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	Page<Course> findByTitleContainingIgnoreCaseAndPriceBetweenAndCourseTypeIdIn(
		    String keyword,
		    Integer minPrice,
		    Integer maxPrice,
		    List<Integer> courseTypeId,
		    Pageable pageable
		);
	
	Page<Course> findByTitleContainingIgnoreCaseAndCourseTypeIdIn(
		    String keyword,
		    List<Integer> courseTypeId,
		    Pageable pageable
		);
	
	Page<Course> findByTitleContainingIgnoreCaseAndPriceBetween(
		    String keyword,
		    Integer minPrice,
		    Integer maxPrice,
		    Pageable pageable
		);
	
	Page<Course> findByTitleContainingIgnoreCase(
		    String keyword,
		    Pageable pageable
		);
}
