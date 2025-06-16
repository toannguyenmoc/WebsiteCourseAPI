package com.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.service.FavoriteService;
import jakarta.validation.Valid;

import com.course.dto.FavoriteRequestDTO;
import com.course.dto.FavoriteResponseDTO;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    @Autowired private FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<FavoriteResponseDTO>> getAllFavorites() {
        return ResponseEntity.ok(favoriteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteResponseDTO> getFavoriteById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(favoriteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FavoriteResponseDTO> createFavorite(@Valid @RequestBody FavoriteRequestDTO dto) {
        return ResponseEntity.ok(favoriteService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FavoriteResponseDTO> updateFavorite(@PathVariable("id") Integer id, @Valid @RequestBody FavoriteRequestDTO dto) {
        return ResponseEntity.ok(favoriteService.update(id, dto)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FavoriteResponseDTO> deleteFavorite(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(favoriteService.deleteById(id));
    }
}
