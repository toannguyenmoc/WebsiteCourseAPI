package com.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.exception.ResourceNotFoundException;
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
    public Favorite update(Favorite favorite) {
    	findById(favorite.getId());
        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite findById(Integer id) {
    	return favoriteRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy"));
    }

    @Override
    public Favorite deleteById(Integer id) {
    	Favorite favorite = findById(id);
    	favoriteRepository.deleteById(id);
        return favorite;
    }
}
