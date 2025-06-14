package com.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.exception.ResourceNotFoundException;
import com.course.model.Commission;
import com.course.repository.CommissionRepository;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    @Override
    public Commission create(Commission commission) {
        return commissionRepository.save(commission);
    }

    @Override
    public List<Commission> findAll() {
        return commissionRepository.findAll();
    }

    @Override
    public Commission update(Commission commission) {
    	findById(commission.getId());
        return commissionRepository.save(commission);
    }

    @Override
    public Commission findById(Integer id) {
    	return commissionRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy"));
    }

    @Override
    public Commission deleteById(Integer id) {
    	Commission commission = findById(id);
    	commissionRepository.deleteById(id);
        return commission;
    }
}
