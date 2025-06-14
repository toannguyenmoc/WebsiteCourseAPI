package com.course.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.course.model.Commission;

@Service
public interface CommissionService {
    
    public Commission create(Commission commission);                   
    public List<Commission> findAll();                            
    public Commission update(Commission commission);
    public Commission findById(Integer id);              
    public Commission deleteById(Integer id); 
}
