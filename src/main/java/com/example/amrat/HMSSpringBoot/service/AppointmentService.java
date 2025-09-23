package com.example.amrat.HMSSpringBoot.service;

import com.example.amrat.HMSSpringBoot.entity.Appointment;
import com.example.amrat.HMSSpringBoot.entity.Doctor;
import com.example.amrat.HMSSpringBoot.entity.Patient;
import com.example.amrat.HMSSpringBoot.repository.AppointmentRepository;
import com.example.amrat.HMSSpringBoot.repository.DoctorRepository;
import com.example.amrat.HMSSpringBoot.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor not found with Id: " + doctorId));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with Id: " + patientId));

        // make sure appointment is not created
        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment should not have been created.");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        // to make bidirectional consistency
        patient.getAppointments().add(appointment);
        doctor.getAppointments().add(appointment);

        appointmentRepository.save(appointment);

        return appointment;
    }

    // we don't need to save appointment because @Transactional context automatically save the appointment because it has been dirtied
    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentID, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentID).orElseThrow(() -> new EntityNotFoundException("Appointment not found with Id: " + appointmentID));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor not found with Id: " + doctorId));

        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment); // to make bidirectional consistency
        return appointment;
    }
}
