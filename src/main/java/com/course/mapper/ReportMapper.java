package com.course.mapper;

import java.util.Date;

import com.course.dto.ReportRequestDTO;
import com.course.dto.ReportResponseDTO;
import com.course.model.Account;
import com.course.model.Course;
import com.course.model.Report;

public class ReportMapper {
    public static Report toEntity(ReportRequestDTO dto) {
        Report report = new Report();

        report.setReason(dto.getReason());
        report.setDescription(dto.getDescription());
        report.setReportDate(dto.getReportDate() != null ? dto.getReportDate() : new Date());

        Course course = new Course();
        course.setId(dto.getCourseId());
        report.setCourse(course);
        
        Account account = new Account();
        account.setId(dto.getAccountId());
        report.setAccount(account);

        return report;
    }

    public static ReportResponseDTO toResponse(Report report) {
        ReportResponseDTO dto = new ReportResponseDTO();
        dto.setId(report.getId());
        dto.setReason(report.getReason());
        dto.setReportDate(report.getReportDate());
        dto.setDescription(report.getDescription());

        if (report.getCourse() != null) {
            dto.setCourseId(report.getCourse().getId());
            dto.setCourseName(report.getCourse().getTitle());
            dto.setTeacherName(report.getCourse().getAccount() != null ? report.getCourse().getAccount().getFullname() : null);
            dto.setEmailTeacher(report.getCourse().getAccount() != null ? report.getCourse().getAccount().getEmail() : null);
        }
        if (report.getAccount() != null) {
            dto.setAccountId(report.getAccount().getId());
            dto.setEmailReport(report.getAccount().getEmail());
        }

        return dto;
    }
}
