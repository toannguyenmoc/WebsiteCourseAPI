package com.course.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.course.dto.StatisticalCourseResponseDTO;
import com.course.repository.StatisticalCourseRepository;

@Service
public class StatisticalServiceImpl implements StatisticalService {
	
	@Autowired
    private StatisticalCourseRepository statisticalCourseRepository;

	@Override
    public ResponseEntity<?> getPagedCourseStatistics(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<StatisticalCourseResponseDTO> result = 
            statisticalCourseRepository.searchCourses(keyword, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return ResponseEntity.ok(response);
    }
}

