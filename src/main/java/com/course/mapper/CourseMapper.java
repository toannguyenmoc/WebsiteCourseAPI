package com.course.mapper;

import java.util.Date;

import com.course.dto.CourseRequestDTO;
import com.course.dto.CourseResponseDTO;
import com.course.model.*;

public class CourseMapper {

    public static Course toEntity(CourseRequestDTO dto, Account account, CourseType courseType, Commission commission) {
        Course course = new Course();
        
        course.setTitle(dto.getTitle());
        course.setSlug(dto.getSlug());
        course.setImage(dto.getImage());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());
        course.setStatus(dto.getStatus());
        course.setCreatedDate(dto.getCreatedDate() != null ? dto.getCreatedDate() : new Date());

        course.setAccount(account);
        course.setCourseType(courseType);
        course.setCommission(commission);

        return course;
    }

    public static CourseResponseDTO toResponse(Course course) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setSlug(course.getSlug());
        dto.setImage(course.getImage());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setCreatedDate(course.getCreatedDate());
        dto.setStatus(course.getStatus());

        dto.setAccountId(course.getAccount().getId());
        dto.setCourseTypeId(course.getCourseType().getId());
        dto.setCommissionId(course.getCommission().getId());
        
        dto.setAccountFullname(course.getAccount().getFullname());
        dto.setCourseTypeName(course.getCourseType().getName());
        dto.setCommissionPercentage(course.getCommission().getPercentage());

        return dto;
    }
}
