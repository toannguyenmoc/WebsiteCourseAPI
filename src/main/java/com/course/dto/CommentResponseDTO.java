package com.course.dto;

import java.util.Date;

import lombok.Data;
@Data
public class CommentResponseDTO {
    private Integer id;
    private Integer rating;
    private String comment;
    private Integer parentComment;
    private Date commentedDate;
    private Integer accountId;
    private String nameAccount;
    private String image;
    private Integer courseId;
}
