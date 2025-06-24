package com.course.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

	 private Integer id;
	 private Double discount;
	 private Date registrationDate ;
	private Integer totalAmount;
    private String transactionCode;
   
    
    private Integer studentId;
    private Integer courseId;
   
    
    private String accountTeacherName;
    private String courseName;
    private String accountStudentName;
}
