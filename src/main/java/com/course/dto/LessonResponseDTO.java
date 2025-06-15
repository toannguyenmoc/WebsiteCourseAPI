package com.course.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LessonResponseDTO {
	
	private Integer id;
	private String title;
	private Integer lesson;
    private String description;
    private String videoUrl;
    private String exerciseUrl;
    private Date postedDate;
    private Boolean status;
    
    private Integer courseId;
    private Integer teacherId;
    private String courseTypeName;
    private Double percentage;
}
