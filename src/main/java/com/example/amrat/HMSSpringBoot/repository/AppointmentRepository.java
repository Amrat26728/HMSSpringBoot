package com.example.amrat.HMSSpringBoot.repository;

import com.example.amrat.HMSSpringBoot.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
