package com.course.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReportResponseDTO {
	private Integer id;
	private String reason;
	private String description;
	@JsonFormat(pattern = "dd/MM/yyyy")
    private Date reportDate;
	private Integer accountId;
	private Integer courseId;
	private String courseName;
	private String emailReport;
	private String emailTeacher;
	private String teacherName;
}
