package com.course.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.course.dto.CommentRequestDTO;
import com.course.dto.CommentResponseDTO;
import com.course.model.Account;
import com.course.model.Comment;
import com.course.model.Course;

public class CommentMapper {
    public static Comment toEntity(CommentRequestDTO dto) {
        Comment comment = new Comment();
        comment.setRating(dto.getRating());
        comment.setComment(dto.getComment());
        comment.setParentComment(dto.getParentComment());
        comment.setCommentedDate(dto.getCommentedDate());

        Account account = new Account();
        account.setId(dto.getAccountId());
        comment.setAccount(account);

        Course course = new Course();
        course.setId(dto.getCourseId());
        comment.setCourse(course);

        return comment;
    }

    public static CommentResponseDTO toResponse(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();

        dto.setId(comment.getId());
        dto.setRating(comment.getRating());
        dto.setComment(comment.getComment());
        dto.setParentComment(comment.getParentComment());
        dto.setCommentedDate(comment.getCommentedDate());

        if (comment.getAccount() != null) {
            dto.setAccountId(comment.getAccount().getId());
        }

        if (comment.getCourse() != null) {
            dto.setCourseId(comment.getCourse().getId());
        }

        return dto;
    }

    public static List<CommentResponseDTO> toResponseDTOList(List<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::toResponse)
                .collect(Collectors.toList());
    }
}
