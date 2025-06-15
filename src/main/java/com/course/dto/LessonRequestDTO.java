package com.course.dto;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LessonRequestDTO {
	
	@NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 250, message = "Độ dài tên phải từ {min} đến {max} ký tự")
	private String title;

	@NotNull
    @Min(value = 1, message = "Bài giảng phải >= 1")
	private Integer lesson;
	
	@NotBlank(message = "Mô tả không được để trống")
    private String description;

	@NotBlank(message = "Video không được để trống")
    private String videoUrl;

    private String exerciseUrl;

    private Date postedDate;

    private Boolean status;
    
    @NotNull(message = "CourseId không được để trống")
    private Integer courseId;
}
