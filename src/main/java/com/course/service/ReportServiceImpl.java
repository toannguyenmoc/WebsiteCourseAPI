package com.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<Report> update(Report report) {
        if (report.getId() == null || !reportRepository.existsById(report.getId())) {
            return Optional.empty();
        }
        return Optional.of(reportRepository.save(report));
    }

    @Override
    public Optional<Report> findById(Integer id) {
        return reportRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        reportRepository.deleteById(id);
    }
}
