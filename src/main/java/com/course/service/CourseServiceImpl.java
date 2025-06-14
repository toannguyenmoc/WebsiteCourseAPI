package com.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.exception.ResourceNotFoundException;
import com.course.model.Course;
import com.course.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course update(Course course) {
        findById(course.getId());
        return courseRepository.save(course);
    }

    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy khoá học"));
    }

    @Override
    public Course deleteById(Integer id) {
    	Course course = findById(id);
        courseRepository.deleteById(id);
        return course;
    }
}
