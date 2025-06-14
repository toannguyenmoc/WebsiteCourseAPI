package com.course.mapper;



import com.course.dto.CourseTypeRequestDTO;
import com.course.dto.CourseTypeResponseDTO;

import com.course.model.CourseType;

public class CourseTypeMapper {
	public static CourseType toEntity(CourseTypeRequestDTO dto) {
	    CourseType courseType = new CourseType();

	    courseType.setName(dto.getName());
	    courseType.setStatus(dto.getStatus());
	    return courseType;
	}
	 public static CourseTypeResponseDTO toResponse(CourseType courseType) {
	        CourseTypeResponseDTO dto = new CourseTypeResponseDTO();
	        dto.setId(courseType.getId());
	        dto.setName(courseType.getName());
	        dto.setStatus(courseType.getStatus());
	    

	        return dto;
	    }
}
