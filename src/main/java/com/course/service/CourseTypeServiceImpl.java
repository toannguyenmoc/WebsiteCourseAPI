package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<CourseType> update(CourseType courseType) {
        if (courseType.getId() == null || !courseTypeRepository.existsById(courseType.getId())) {
            return Optional.empty();
        }
        return Optional.of(courseTypeRepository.save(courseType));
    }

    @Override
    public Optional<CourseType> findById(Integer id) {
        return courseTypeRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        courseTypeRepository.deleteById(id);
    }
}
