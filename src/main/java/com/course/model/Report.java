package com.course.model;

import java.util.Date;

import jakarta.persistence.Column;
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
@Table(name = "Reports")
public class Report {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(columnDefinition = "nvarchar(max)")
	private String reason;
	
	@Column(columnDefinition = "nvarchar(max)")
	private String description;

	@Temporal(TemporalType.DATE)
    private Date reportDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "accountId", nullable = false)
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "courseId", nullable = false)
	private Course course;
}
