package com.course.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReportRequestDTO {
	@NotBlank(message = "Tiêu đề không được để trống")
	private String reason;

	private String description;

    private Date reportDate;
    
    @NotNull(message = "AccountId không được để trống")
	private Integer accountId;
    
    @NotNull(message = "CourseId không được để trống")
	private Integer courseId;
}
