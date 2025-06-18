package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.service.StatisticalService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticalController {
	
	@Autowired
    private StatisticalService statisticalService;

    @GetMapping("/courses")
    public ResponseEntity<?> getPagedCourseStatistics(
            @RequestParam(name = "keyword",defaultValue = "") String keyword,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size) {
        return statisticalService.getPagedCourseStatistics(keyword, page, size);
    }
}

