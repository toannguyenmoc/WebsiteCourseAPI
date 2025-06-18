package com.course.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.course.dto.StatisticalCourseResponseDTO;
import com.course.model.Course;

public interface StatisticalCourseRepository extends JpaRepository<Course, Integer> {
	@Query(value = """
		    SELECT 
		        c.id AS courseId,
		        c.title AS courseName,
		        COUNT(p.id) AS totalStudent,
		        COALESCE(SUM(p.total_amount), 0) AS totalRevenue,
		        a.fullname AS teacherName,
		        COALESCE(AVG(cm.rating), 0) AS rating,
		        c.created_date AS postedDate,
		        COUNT(cm.id) AS totalComment
		    FROM courses c
		    JOIN accounts a ON c.account_id = a.id
		    LEFT JOIN payments p ON p.course_id = c.id
		    LEFT JOIN comments cm ON cm.course_id = c.id
		    WHERE (
		        LOWER(c.title) LIKE CONCAT('%', LOWER(:keyword), '%')
		        OR NOT EXISTS (
		            SELECT 1
		            FROM STRING_SPLIT(:keyword, ' ') AS s
		            WHERE REPLACE(LOWER(c.title), ' ', '') NOT LIKE '%' + LOWER(s.value) + '%'
		        )
		    )
		    GROUP BY c.id, c.title, a.fullname, c.created_date
		    ORDER BY COALESCE(SUM(p.total_amount), 0) DESC
		""",
		countQuery = """
		    SELECT COUNT(*)
		    FROM courses c
		    WHERE (
		        LOWER(c.title) LIKE CONCAT('%', LOWER(:keyword), '%')
		        OR NOT EXISTS (
		            SELECT 1
		            FROM STRING_SPLIT(:keyword, ' ') AS s
		            WHERE REPLACE(LOWER(c.title), ' ', '') NOT LIKE '%' + LOWER(s.value) + '%'
		        )
		    )
		""", nativeQuery = true)
		Page<StatisticalCourseResponseDTO> searchCourses(@Param("keyword") String keyword, Pageable pageable);


}
