package com.course.dto;

import java.util.Date;

import lombok.Data;
@Data
public class PaymentResponseDTO {

	 private Integer id;
	private Integer totalAmount;
    private Double discount;
    private String transactionCode;
    private Date registrationDate ;
    
    private Integer accountId;
    private Integer courseId;
    
    private String accountFullname;
    private String courseName;
}
