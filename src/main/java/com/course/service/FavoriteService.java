package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.dto.FavoriteRequestDTO;
import com.course.dto.FavoriteResponseDTO;

@Service
public interface FavoriteService {

    public FavoriteResponseDTO create(FavoriteRequestDTO favoriteRequestDTO);                   
    public List<FavoriteResponseDTO> findAll();                            
    public FavoriteResponseDTO update(Integer id, FavoriteRequestDTO favoriteRequestDTO);
    public FavoriteResponseDTO findById(Integer id);              
    public FavoriteResponseDTO deleteById(Integer id); 
}
