package com.course.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CommissionRequestDTO {

	@NotNull(message = "Phần trăm không được để trống")
	 private Double percentage;
	@NotNull(message = "Ngày không được để trống")
	 @JsonFormat(pattern = "dd/MM/yyyy")
	 private Date effectiveDate;
}
