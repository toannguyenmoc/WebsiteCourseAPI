package com.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.exception.ResourceNotFoundException;
import com.course.model.Lesson;
import com.course.repository.LessonRepository;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Lesson create(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson update(Lesson lesson) {
    	findById(lesson.getId());
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson findById(Integer id) {
    	return lessonRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy"));
    }

    @Override
    public Lesson deleteById(Integer id) {
    	Lesson lesson = findById(id);
    	lessonRepository.deleteById(id);
        return lesson;
    }
}
