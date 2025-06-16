package com.course.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FavoriteResponseDTO {
	
	private Integer id;
	private Date addedDate;
	
	private Integer accountId;
	private Integer courseId;
}
