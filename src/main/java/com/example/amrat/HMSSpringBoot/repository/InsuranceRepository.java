package com.example.amrat.HMSSpringBoot.repository;

import com.example.amrat.HMSSpringBoot.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
