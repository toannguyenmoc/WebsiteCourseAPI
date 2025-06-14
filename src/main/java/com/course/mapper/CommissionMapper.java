package com.course.mapper;

import com.course.dto.CommissionRequestDTO;
import com.course.dto.CommissionResponseDTO;

import com.course.model.Commission;


public class CommissionMapper {
	public static Commission toEntity(CommissionRequestDTO dto) {
		Commission commission = new Commission();

		commission.setPercentage(dto.getPercentage());
		commission.setEffectiveDate(dto.getEffectiveDate());
	    return commission;
	}
	 public static CommissionResponseDTO toResponse(Commission commission) {
		 CommissionResponseDTO dto = new CommissionResponseDTO();
	        dto.setId(commission.getId());
	        dto.setPercentage(commission.getPercentage());
	        dto.setEffectiveDate(commission.getEffectiveDate());
	    

	        return dto;
	    }
}
