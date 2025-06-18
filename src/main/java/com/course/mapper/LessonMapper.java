package com.course.mapper;

import java.util.Date;

import com.course.dto.LessonRequestDTO;
import com.course.dto.LessonResponseDTO;
import com.course.model.Course;
import com.course.model.Lesson;

public class LessonMapper {
	public static Lesson toEntity(LessonRequestDTO dto) {
		Lesson lesson = new Lesson();

		lesson.setTitle(dto.getTitle());
		lesson.setLesson(dto.getLesson());
	    lesson.setDescription(dto.getDescription());
	    lesson.setVideoUrl(dto.getVideoUrl());
	    lesson.setExerciseUrl(dto.getExerciseUrl());
	    lesson.setPostedDate(dto.getPostedDate() != null ? dto.getPostedDate() : new Date());
	    lesson.setStatus(dto.getStatus() != null ? dto.getStatus() : true);
	    
	    Course course = new Course();
	    course.setId(dto.getCourseId());
	    lesson.setCourse(course);

	    return lesson;
	}


    public static LessonResponseDTO toResponse(Lesson lesson) {
    	LessonResponseDTO dto = new LessonResponseDTO();
        dto.setId(lesson.getId());
        dto.setTitle(lesson.getTitle());
        dto.setLesson(lesson.getLesson());
        dto.setDescription(lesson.getDescription());
        dto.setVideoUrl(lesson.getVideoUrl());
        dto.setExerciseUrl(lesson.getExerciseUrl());
        dto.setPostedDate(lesson.getPostedDate());
        dto.setStatus(lesson.getStatus());
        
        if (lesson.getCourse() != null) {
            dto.setCourseId(lesson.getCourse().getId());
            dto.setCourseName(lesson.getCourse().getTitle());

            if (lesson.getCourse().getAccount() != null) {
                dto.setTeacherId(lesson.getCourse().getAccount().getId());
            }

          
                dto.setCourseName(lesson.getCourse().getTitle());
            

        }
        
        return dto;
    }
}
