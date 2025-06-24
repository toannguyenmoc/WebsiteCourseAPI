package com.course.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class PaymentRequestDTO {

	
	private Integer totalAmount;

    private Double discount;

    private String transactionCode;
    
    private Date registrationDate = new Date() ;
    
    @NotNull(message = "AccountId không được để trống")
    private Integer accountId;
    
    private Integer role;
    
    @NotNull(message = "CourseId không được để trống")
    private Integer courseId;

}
