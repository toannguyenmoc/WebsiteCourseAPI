package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<Commission> update(Commission commission) {
        if (commission.getId() == null || !commissionRepository.existsById(commission.getId())) {
            return Optional.empty();
        }
        return Optional.of(commissionRepository.save(commission));
    }

    @Override
    public Optional<Commission> findById(Integer id) {
        return commissionRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        commissionRepository.deleteById(id);
    }
}
