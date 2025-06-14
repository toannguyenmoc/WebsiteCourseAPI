package com.course.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dto.CourseTypeRequestDTO;
import com.course.dto.CourseTypeResponseDTO;
import com.course.exception.ResourceNotFoundException;

import com.course.mapper.CourseTypeMapper;

import com.course.model.CourseType;
import com.course.repository.CourseTypeRepository;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

	@Autowired
	private CourseTypeRepository courseTypeRepository;

	@Override
	public CourseTypeResponseDTO create(CourseTypeRequestDTO courseTypeRequestDTO) {
		CourseType courseType = CourseTypeMapper.toEntity(courseTypeRequestDTO);
		CourseType saved = courseTypeRepository.save(courseType);
		CourseTypeResponseDTO courseTypeResponseDTO = CourseTypeMapper.toResponse(saved);
		return courseTypeResponseDTO;
	}

	@Override
	public List<CourseTypeResponseDTO> findAll() {
		List<CourseTypeResponseDTO> responseList = courseTypeRepository.findAll().stream()
				.map(CourseTypeMapper::toResponse).sorted(Comparator.comparing(CourseTypeResponseDTO::getName)
						.reversed().thenComparing(CourseTypeResponseDTO::getStatus).reversed())
				.collect(Collectors.toList());
		return responseList;
	}

	@Override
	public CourseTypeResponseDTO update(Integer id, CourseTypeRequestDTO courseTypeRequestDTO) {
		findById(id);
		CourseType updatedCourseType = CourseTypeMapper.toEntity(courseTypeRequestDTO);
		updatedCourseType.setId(id);
		CourseType update = courseTypeRepository.save(updatedCourseType);
		CourseTypeResponseDTO courseTypeResponseDTO = CourseTypeMapper.toResponse(update);
		return courseTypeResponseDTO;
	}

	@Override
	public CourseTypeResponseDTO findById(Integer id) {
		CourseType courseType = courseTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy khoá học"));
		CourseTypeResponseDTO courseTypeResponseDTO = CourseTypeMapper.toResponse(courseType);
		return courseTypeResponseDTO;
	}

	@Override
	public CourseTypeResponseDTO deleteById(Integer id) {
		CourseTypeResponseDTO courseTypeResponseDTO = findById(id);
		courseTypeRepository.deleteById(id);
		return courseTypeResponseDTO;
	}
}
