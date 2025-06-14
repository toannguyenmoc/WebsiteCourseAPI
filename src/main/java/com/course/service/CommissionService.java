package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.model.Commission;

@Service
public interface CommissionService {
    
    public Commission create(Commission commission);                   
    public List<Commission> findAll();                            
    public Optional<Commission> update(Commission commission);
    public Optional<Commission> findById(Integer id);              
    public void deleteById(Integer id); 
}
