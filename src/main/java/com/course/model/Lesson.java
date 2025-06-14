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
@Table(name = "Lessons")
public class Lesson {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(columnDefinition = "nvarchar(250)")
	private String title;

	private Integer lesson;
	
	@Column(columnDefinition = "nvarchar(max)")
    private String description;

    private String videoUrl;

    private String exerciseUrl;

    @Temporal(TemporalType.DATE)
    private Date postedDate = new Date();

    private Boolean status;
    
    @ManyToOne
    @JoinColumn(name = "courseId", nullable = false)
    private Course course;
}
