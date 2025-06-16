package com.course.dto;

import java.util.Date;

import lombok.Data;
@Data
public class CommissionResponseDTO {

	  private Integer id;
	private Double percentage;
	 private Date effectiveDate;
}
