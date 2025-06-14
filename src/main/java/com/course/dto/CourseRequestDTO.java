package com.course.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class CourseRequestDTO {

    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;

    @NotBlank(message = "Slug không được để trống")
    private String slug;

    private String image;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotNull
    @Min(value = 0, message = "Giá phải >= 0")
    private Integer price;

    private Boolean status;

    private Date createdDate;

    @NotNull(message = "AccountId không được để trống")
    private Integer accountId;

    @NotNull(message = "CourseTypeId không được để trống")
    private Integer courseTypeId;

    @NotNull(message = "CommissionId không được để trống")
    private Integer commissionId;
}
