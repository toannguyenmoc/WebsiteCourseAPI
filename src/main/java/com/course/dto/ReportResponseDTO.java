package com.course.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReportResponseDTO {
	private Integer id;
	private String reason;
    private Date reportDate;

	private Integer accountId;
	private Integer courseId;
}
