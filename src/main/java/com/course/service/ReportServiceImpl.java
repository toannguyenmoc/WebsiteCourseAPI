package com.course.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.dto.ReportRequestDTO;
import com.course.dto.ReportResponseDTO;
import com.course.exception.ResourceNotFoundException;
import com.course.mapper.ReportMapper;
import com.course.model.Report;
import com.course.repository.ReportRepository;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public ReportResponseDTO create(ReportRequestDTO reportRequestDTO) {
        Report report = ReportMapper.toEntity(reportRequestDTO);
        Report saved = reportRepository.save(report);
        ReportResponseDTO reportResponseDTO = ReportMapper.toResponse(saved);
        return reportResponseDTO;
    }

    @Override
    public List<ReportResponseDTO> findAll() {
        List<ReportResponseDTO> responseList = reportRepository.findAll().stream()
                .map(ReportMapper::toResponse)
                .sorted(Comparator.comparing(ReportResponseDTO::getReportDate).reversed())
                .collect(Collectors.toList());
        return responseList;
    }

    @Override
    public ReportResponseDTO update(Integer id, ReportRequestDTO reportRequestDTO) {
        findById(id); // kiểm tra tồn tại
        Report updatedReport = ReportMapper.toEntity(reportRequestDTO);
        updatedReport.setId(id);
        Report update = reportRepository.save(updatedReport);
        ReportResponseDTO reportResponseDTO = ReportMapper.toResponse(update);
        return reportResponseDTO;
    }

    @Override
    public ReportResponseDTO findById(Integer id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("không tìm thấy báo cáo"));
        ReportResponseDTO reportResponseDTO = ReportMapper.toResponse(report);
        return reportResponseDTO;
    }

    @Override
    public ReportResponseDTO deleteById(Integer id) {
        ReportResponseDTO reportResponseDTO = findById(id); // lấy trước để trả về
        reportRepository.deleteById(id);
        return reportResponseDTO;
    }
}
