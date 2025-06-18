package com.course.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StatisticalService {
	ResponseEntity<?> getPagedCourseStatistics(String keyword, int page, int size);
}

