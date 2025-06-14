package com.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.exception.ResourceNotFoundException;
import com.course.model.Report;
import com.course.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report create(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report update(Report report) {
    	findById(report.getId());
        return reportRepository.save(report);
    }

    @Override
    public Report findById(Integer id) {
    	return reportRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("không tìm thấy"));
    }

    @Override
    public Report deleteById(Integer id) {
    	Report report = findById(id);
    	reportRepository.deleteById(id);
        return report;
    }
}
