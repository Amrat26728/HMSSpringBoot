package com.example.amrat.HMSSpringBoot.repository;

import com.example.amrat.HMSSpringBoot.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
