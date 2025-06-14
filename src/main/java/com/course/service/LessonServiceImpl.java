package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<Lesson> update(Lesson lesson) {
        if (lesson.getId() == null || !lessonRepository.existsById(lesson.getId())) {
            return Optional.empty();
        }
        return Optional.of(lessonRepository.save(lesson));
    }

    @Override
    public Optional<Lesson> findById(Integer id) {
        return lessonRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        lessonRepository.deleteById(id);
    }
}
