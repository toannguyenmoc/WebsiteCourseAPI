package com.course.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dto.CommissionRequestDTO;
import com.course.dto.CommissionResponseDTO;

import com.course.exception.ResourceNotFoundException;
import com.course.mapper.CommissionMapper;

import com.course.model.Commission;

import com.course.repository.CommissionRepository;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    @Override
    public CommissionResponseDTO create(CommissionRequestDTO commissionRequestDTO) {
		Commission commission = CommissionMapper.toEntity(commissionRequestDTO);
		Commission saved = commissionRepository.save(commission);
		CommissionResponseDTO commissionResponseDTO = CommissionMapper.toResponse(saved);
		return commissionResponseDTO;
	}

    @Override
    public List<CommissionResponseDTO> findAll() {
		List<CommissionResponseDTO> responseList = commissionRepository.findAll().stream()
				.map(CommissionMapper::toResponse).sorted(Comparator.comparing(CommissionResponseDTO::getPercentage)
						.reversed().thenComparing(CommissionResponseDTO::getEffectiveDate).reversed())
				.collect(Collectors.toList());
		return responseList;
    }

    @Override
    public CommissionResponseDTO update(Integer id, CommissionRequestDTO commissionRequestDTO) {
		findById(id);
		Commission updatedcommission = CommissionMapper.toEntity(commissionRequestDTO);
		updatedcommission.setId(id);
		Commission update = commissionRepository.save(updatedcommission);
		CommissionResponseDTO commissionResponseDTO = CommissionMapper.toResponse(update);
		return commissionResponseDTO;
    }

    @Override
    public CommissionResponseDTO findById(Integer id) {
    	Commission commission = commissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy khoá học"));
    	CommissionResponseDTO commissionResponseDTO = CommissionMapper.toResponse(commission);
		return commissionResponseDTO;
    }

    @Override
    public CommissionResponseDTO deleteById(Integer id) {
    	CommissionResponseDTO commissionResponseDTO = findById(id);
    	commissionRepository.deleteById(id);
		return commissionResponseDTO;
    }
}
