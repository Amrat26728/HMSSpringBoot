package com.example.amrat.HMSSpringBoot.repository;

import com.example.amrat.HMSSpringBoot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
