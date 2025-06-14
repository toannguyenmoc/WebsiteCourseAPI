package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.model.Favorite;
import com.course.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public Favorite create(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Optional<Favorite> update(Favorite favorite) {
        if (favorite.getId() == null || !favoriteRepository.existsById(favorite.getId())) {
            return Optional.empty();
        }
        return Optional.of(favoriteRepository.save(favorite));
    }

    @Override
    public Optional<Favorite> findById(Integer id) {
        return favoriteRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        favoriteRepository.deleteById(id);
    }
}
