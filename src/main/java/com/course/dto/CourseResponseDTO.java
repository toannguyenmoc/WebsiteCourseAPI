package com.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {
    private Integer id;
    private String title;
    private String slug;
    private String image;
    private String description;
    private Integer price;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    private Boolean status;

    private Integer accountId;
    private Integer courseTypeId;
    private Integer commissionId;
    
    private String accountTeacher;
    private String courseTypeName;
    private Double commissionPercentage;
    
    private int lessonCount;
    
    @JsonFormat()
    private double totalRating;
}
