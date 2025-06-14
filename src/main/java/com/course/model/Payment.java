package com.course.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Payments")
public class Payment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private Integer totalAmount;

    private Double discount;

    private String transactionCode;

    @Temporal(TemporalType.DATE)
    private Date registrationDate = new Date();
    
    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;
    
    @ManyToOne
	@JoinColumn(name = "courseId", nullable = false)
	private Course course;
}
