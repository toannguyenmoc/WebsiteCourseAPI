package com.course.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.course.dto.CourseResponseDTO;
import com.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	@Query(value = """
	        SELECT 
	            c.id AS id,
	            c.title AS title,
	            c.slug AS slug,
	            c.image AS image,
	            c.description AS description,
	            c.price AS price,
	            c.created_date AS createdDate,
	            c.status AS status,
	            c.account_id AS accountId,
	            c.course_type_id AS courseTypeId,
	            c.commission_id AS commissionId,
	            a.fullname AS accountFullname,
	            ct.name AS courseTypeName,
	            cm.percentage AS commissionPercentage
	        FROM courses c
	        JOIN accounts a ON c.account_id = a.id
	        JOIN course_types ct ON c.course_type_id = ct.id
	        JOIN commissions cm ON c.commission_id = cm.id
	    """, 
	    countQuery = "SELECT COUNT(*) FROM courses",
	    nativeQuery = true)
	Page<CourseResponseDTO> findPaged(Pageable pageable);

}
