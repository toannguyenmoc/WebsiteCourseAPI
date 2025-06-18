package com.course.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommissionResponseDTO {

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date effectiveDate;
	private Double percentage;
}
