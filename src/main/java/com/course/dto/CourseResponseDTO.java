package com.course.dto;

import lombok.Data;

import java.util.Date;

import com.course.model.Account;

@Data
public class CourseResponseDTO {
    private Integer id;
    private String title;
    private String slug;
    private String image;
    private String description;
    private Integer price;
    private Date createdDate;
    private Boolean status;

    private Integer accountId;
    private Integer courseTypeId;
    private Integer commissionId;
    
    private String accountFullname;
    private String courseTypeName;
    private Double commissionPercentage;
    
    private Account account;
    
}
