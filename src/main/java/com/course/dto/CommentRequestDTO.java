package com.course.dto;

import java.util.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CommentRequestDTO {
    @NotNull(message = "Rating không được để trống")
    @Min(value = 1, message = "Rating phải lớn hơn hoặc bằng 1")
    @Max(value = 5, message = "Rating phải nhỏ hơn hoặc bằng 5")
    private Integer rating;

    @NotBlank(message = "Comment không được để trống")
    private String comment;
    @NotNull(message = "Parent comment không được để trống")
    private Integer parentComment;

    private Date commentedDate = new Date();

    @NotNull(message = "Account ID không được để trống")
    private Integer accountId;

    @NotNull(message = "Course ID không được để trống")
    private Integer courseId;
}
