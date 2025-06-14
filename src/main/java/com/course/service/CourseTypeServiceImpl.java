package com.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.exception.ResourceNotFoundException;
import com.course.model.CourseType;
import com.course.repository.CourseTypeRepository;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    @Override
    public CourseType create(CourseType courseType) {
        return courseTypeRepository.save(courseType);
    }

    @Override
    public List<CourseType> findAll() {
        return courseTypeRepository.findAll();
    }

    @Override
    public CourseType update(CourseType courseType) {
    	findById(courseType.getId());
        return courseTypeRepository.save(courseType);
    }

    @Override
    public CourseType findById(Integer id) {
    	return courseTypeRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy"));
    }

    @Override
    public CourseType deleteById(Integer id) {
    	CourseType courseType = findById(id);
    	courseTypeRepository.deleteById(id);
        return courseType;
    }
}
