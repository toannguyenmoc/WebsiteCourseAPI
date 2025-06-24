package com.course.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class CourseRequestDTO {

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 250, message = "Độ dài tên phải từ {min} đến {max} ký tự")
    private String title;

    @NotBlank(message = "Slug không được để trống")
    private String slug;

    @NotBlank(message = "Hình không được để trống")
    private String image;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotNull
    @Min(value = 0, message = "Giá phải >= 0")
    private Integer price;

    private Boolean status;
    
    @NotNull(message = "Ngày không được để trống")
    private Date createdDate;

    @NotNull(message = "AccountId không được để trống")
    private Integer accountId;

    @NotNull(message = "CourseTypeId không được để trống")
    private Integer courseTypeId;

    @NotNull(message = "CommissionId không được để trống")
    private Integer commissionId;
}
