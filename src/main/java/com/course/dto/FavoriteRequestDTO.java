package com.course.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FavoriteRequestDTO {
	
	private Date addedDate = new Date();
	
	@NotNull(message = "AccountId không được để trống")
	private Integer accountId;
	 
	@NotNull(message = "courseId không được để trống")
	private Integer courseId;
}
