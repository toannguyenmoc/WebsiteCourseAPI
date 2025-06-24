package com.course.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.course.dto.LessonRequestDTO;
import com.course.dto.LessonResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.LessonMapper;
import com.course.model.Lesson;
import com.course.repository.LessonRepository;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

	@Override
	public LessonResponseDTO create(LessonRequestDTO lessonRequestDTO) {
		Lesson lesson = LessonMapper.toEntity(lessonRequestDTO);
		Lesson saved = lessonRepository.save(lesson);
		LessonResponseDTO lessonResponseDTO = LessonMapper.toResponse(saved);
		return lessonResponseDTO;
	}

	@Override
	public List<LessonResponseDTO> findAll() {
		List<LessonResponseDTO> responseList = lessonRepository.findAll().stream().map(LessonMapper::toResponse)
				.sorted(Comparator.comparing(LessonResponseDTO::getTitle).reversed())
				.collect(Collectors.toList());
		return responseList;
	}

	@Override
	public LessonResponseDTO update(Integer id, LessonRequestDTO lessonRequestDTO) {
		findById(id);
		Lesson updatedLesson = LessonMapper.toEntity(lessonRequestDTO);
		updatedLesson.setId(id);
		Lesson update = lessonRepository.save(updatedLesson);
		LessonResponseDTO lessonResponseDTO = LessonMapper.toResponse(update);
		return lessonResponseDTO;
	}

	@Override
	public LessonResponseDTO findById(Integer id) {
		Lesson lesson = lessonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy bài học"));
		LessonResponseDTO lessonResponseDTO = LessonMapper.toResponse(lesson);
		return lessonResponseDTO;
	}

	@Override
	public LessonResponseDTO deleteById(Integer id) {
		LessonResponseDTO lessonResponseDTO = findById(id);
		lessonRepository.deleteById(id);
		return lessonResponseDTO;
	}
	
	@Override
	public ResponseEntity<?> getPagedLessons(Integer courseId,int page, int size) {
	    Pageable pageable = PageRequest.of(page, size, Sort.by("lesson").descending());
	    Page<Lesson> list;
		if(courseId == null){
			list = lessonRepository.findAll(pageable);
		}else{
			list = lessonRepository.findByCourseId(courseId, pageable);
		}
	    Page<LessonResponseDTO> result = list.map(LessonMapper::toResponse);

	    Map<String, Object> response = new HashMap<>();
	    response.put("data", result.getContent());
	    response.put("currentPage", result.getNumber());
	    response.put("totalItems", result.getTotalElements());
	    response.put("totalPages", result.getTotalPages());

	    return ResponseEntity.ok(response);
	}

	@Override
	public List<LessonResponseDTO> getPagedLessonss(int page, int size, Integer userId) {
		 Pageable pageable = PageRequest.of(page, size);
		    Page<Lesson> list = lessonRepository.findLessonsByAccountIdAndRole2Native(userId, pageable);
		    List<LessonResponseDTO> dto = list.stream().map(LessonMapper::toResponse).toList();
		    
		return dto;
	}
	


}
