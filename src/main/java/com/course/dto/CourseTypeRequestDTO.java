package com.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CourseTypeRequestDTO {
	  @NotBlank(message = "Tên không được để trống")
	  private String name;
	  private Boolean status;
}
