package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.model.Favorite;

@Service
public interface FavoriteService {
    
    public Favorite create(Favorite favorite);                   
    public List<Favorite> findAll();                            
    public Favorite update(Favorite favorite);
    public Favorite findById(Integer id);              
    public Favorite deleteById(Integer id); 
}
