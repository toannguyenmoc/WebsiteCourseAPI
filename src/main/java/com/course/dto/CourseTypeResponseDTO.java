package com.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTypeResponseDTO {
	private Integer id;
	 private String name;
	  private Boolean status;
	  
}
