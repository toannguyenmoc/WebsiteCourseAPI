package com.course.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
}
