package com.course.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticalCourseResponseDTO {
	private Integer courseId;
	private String courseName;
	private int totalStudent;
	private long totalRevenue;
	private String teacherName;
	private double rating;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date postedDate;
	private long totalComment;
}
