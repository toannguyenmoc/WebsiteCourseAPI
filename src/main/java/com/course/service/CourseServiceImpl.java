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

import com.course.dto.CourseRequestDTO;
import com.course.dto.CourseResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.CourseMapper;
import com.course.model.Course;
import com.course.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public CourseResponseDTO create(CourseRequestDTO courseRequestDTO) {
		Course course = CourseMapper.toEntity(courseRequestDTO);
		Course saved = courseRepository.save(course);
		CourseResponseDTO courseResponseDTO = CourseMapper.toResponse(saved);
		return courseResponseDTO;
	}

	@Override
	public List<CourseResponseDTO> findAll() {
		List<CourseResponseDTO> responseList = courseRepository.findAll().stream().map(CourseMapper::toResponse)
				.sorted(Comparator.comparing(CourseResponseDTO::getTitle).reversed()
						.thenComparing(CourseResponseDTO::getPrice).reversed()
						.thenComparing(CourseResponseDTO::getCreatedDate))
				.collect(Collectors.toList());
		return responseList;
	}

	@Override
	public CourseResponseDTO update(Integer id, CourseRequestDTO courseRequestDTO) {
		findById(id);
		Course updatedCourse = CourseMapper.toEntity(courseRequestDTO);
		updatedCourse.setId(id);
		Course update = courseRepository.save(updatedCourse);
		CourseResponseDTO courseResponseDTO = CourseMapper.toResponse(update);
		return courseResponseDTO;
	}

	@Override
	public CourseResponseDTO findById(Integer id) {
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy khoá học"));
		CourseResponseDTO courseResponseDTO = CourseMapper.toResponse(course);
		return courseResponseDTO;
	}

	@Override
	public CourseResponseDTO deleteById(Integer id) {
		CourseResponseDTO courseResponseDTO = findById(id);
		courseRepository.deleteById(id);
		return courseResponseDTO;
	}

	@Override
	public ResponseEntity<?> getPagedCoursesByManyParams(
			int page, int size, String keyword, Integer minPrice,
			Integer maxPrice, List<Integer> courseTypeIds) {

		Pageable pageable = PageRequest.of(page, size,
				Sort.by("createdDate").descending().and(Sort.by("title").ascending()));
		Page<Course> list;

		if (minPrice != null && maxPrice != null && !courseTypeIds.isEmpty()) {
			list = courseRepository.findByTitleContainingIgnoreCaseAndPriceBetweenAndCourseTypeIdIn(keyword, minPrice,
					maxPrice, courseTypeIds, pageable);
		} else if (minPrice == null && maxPrice == null && !courseTypeIds.isEmpty()) {
			list = courseRepository.findByTitleContainingIgnoreCaseAndCourseTypeIdIn(keyword, courseTypeIds, pageable);
		} else if(minPrice != null && maxPrice != null && courseTypeIds.isEmpty()) {
			list = courseRepository.findByTitleContainingIgnoreCaseAndPriceBetween(keyword, minPrice, maxPrice, pageable);
		}else if(minPrice == null && maxPrice == null && courseTypeIds.isEmpty()) {
			list = courseRepository.findByTitleContainingIgnoreCase(keyword, pageable);
		}else {
			list = courseRepository.findAll(pageable);			
		}

		Page<CourseResponseDTO> result = list.map(CourseMapper::toResponse);

		Map<String, Object> response = new HashMap<>();
		response.put("data", result.getContent());
		response.put("currentPage", result.getNumber());
		response.put("totalItems", result.getTotalElements());
		response.put("totalPages", result.getTotalPages());

		return ResponseEntity.ok(response);
	};
	
	@Override
	public ResponseEntity<?> findByAccountId(Integer accountId, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size,
	            Sort.by("createdDate").descending().and(Sort.by("title").ascending()));

	    Page<Course> coursePage = courseRepository.findByAccountId(accountId, pageable);
	    Page<CourseResponseDTO> result = coursePage.map(CourseMapper::toResponse);

	    Map<String, Object> response = new HashMap<>();
	    response.put("data", result.getContent());
	    response.put("currentPage", result.getNumber());
	    response.put("totalItems", result.getTotalElements());
	    response.put("totalPages", result.getTotalPages());

	    return ResponseEntity.ok(response);
	}



	
	public Boolean existSlug(String slug) {
		return courseRepository.existsBySlug(slug);
	}

}
