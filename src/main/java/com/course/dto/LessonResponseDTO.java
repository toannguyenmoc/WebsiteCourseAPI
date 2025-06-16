package com.course.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
