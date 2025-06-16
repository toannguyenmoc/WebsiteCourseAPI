package com.course.mapper;

import java.util.Date;

import com.course.dto.FavoriteRequestDTO;
import com.course.dto.FavoriteResponseDTO;
import com.course.model.Account;
import com.course.model.Course;
import com.course.model.Favorite;

public class FavoriteMapper {
    public static Favorite toEntity(FavoriteRequestDTO dto) {
        Favorite favorite = new Favorite();

        favorite.setAddedDate(dto.getAddedDate() != null ? dto.getAddedDate() : new Date());
        
        Course course = new Course();
        favorite.setId(dto.getCourseId());
        favorite.setCourse(course);
        
        Account account = new Account();
        favorite.setId(dto.getAccountId());
        favorite.setAccount(account);

        return favorite;
    }


    public static FavoriteResponseDTO toResponse(Favorite favorite) {
        FavoriteResponseDTO dto = new FavoriteResponseDTO();
        dto.setId(favorite.getId());
        dto.setAddedDate(favorite.getAddedDate());
        
        if (favorite.getCourse() != null) {
            dto.setCourseId(favorite.getCourse().getId());
        }
        
        if (favorite.getAccount() != null) {
            dto.setAccountId(favorite.getAccount().getId());
        }
        
        return dto;
    }
}
