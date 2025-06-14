package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

}
