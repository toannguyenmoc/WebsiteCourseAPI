package com.course.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionResponseDTO {

	  private Integer id;
	
	 private Date effectiveDate;
	 private Double percentage;
}
