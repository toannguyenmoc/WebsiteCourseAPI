package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.model.Favorite;

@Service
public interface FavoriteService {
    
    public Favorite create(Favorite favorite);                   
    public List<Favorite> findAll();                            
    public Optional<Favorite> update(Favorite favorite);
    public Optional<Favorite> findById(Integer id);              
    public void deleteById(Integer id); 
}
