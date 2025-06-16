package com.course.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dto.FavoriteRequestDTO;
import com.course.dto.FavoriteResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.FavoriteMapper;
import com.course.model.Favorite;
import com.course.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public FavoriteResponseDTO create(FavoriteRequestDTO favoriteRequestDTO) {
        Favorite favorite = FavoriteMapper.toEntity(favoriteRequestDTO);
        Favorite saved = favoriteRepository.save(favorite);
        FavoriteResponseDTO favoriteResponseDTO = FavoriteMapper.toResponse(saved);
        return favoriteResponseDTO;
    }

    @Override
    public List<FavoriteResponseDTO> findAll() {
        List<FavoriteResponseDTO> responseList = favoriteRepository.findAll().stream()
                .map(FavoriteMapper::toResponse)
                .sorted(Comparator.comparing(FavoriteResponseDTO::getAddedDate).reversed())
                .collect(Collectors.toList());
        return responseList;
    }

    @Override
    public FavoriteResponseDTO update(Integer id, FavoriteRequestDTO favoriteRequestDTO) {
        findById(id); // kiểm tra tồn tại
        Favorite updatedFavorite = FavoriteMapper.toEntity(favoriteRequestDTO);
        updatedFavorite.setId(id);
        Favorite update = favoriteRepository.save(updatedFavorite);
        FavoriteResponseDTO favoriteResponseDTO = FavoriteMapper.toResponse(update);
        return favoriteResponseDTO;
    }

    @Override
    public FavoriteResponseDTO findById(Integer id) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("không tìm thấy mục yêu thích"));
        FavoriteResponseDTO favoriteResponseDTO = FavoriteMapper.toResponse(favorite);
        return favoriteResponseDTO;
    }

    @Override
    public FavoriteResponseDTO deleteById(Integer id) {
        FavoriteResponseDTO favoriteResponseDTO = findById(id); // lấy trước để trả về
        favoriteRepository.deleteById(id);
        return favoriteResponseDTO;
    }
}
