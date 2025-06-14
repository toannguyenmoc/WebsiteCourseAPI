package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.model.Report;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
