package com.example.amrat.HMSSpringBoot.service;

import com.example.amrat.HMSSpringBoot.entity.Insurance;
import com.example.amrat.HMSSpringBoot.entity.Patient;
import com.example.amrat.HMSSpringBoot.repository.InsuranceRepository;
import com.example.amrat.HMSSpringBoot.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


    // after executing this method
    // dirty checking will be checked
    // it will find patient has been dirtied
    // then it will check insurance has added but insurance does not exist
    // so that we have defined CascadeType.MERGE so it will add insurance first
    // then insurance id will be saved to the patient
    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId){
        // patient is in persistence
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        // made patient dirty
        // this will save insurance in the DB then attach insurance id to the patient
        patient.setInsurance(insurance);

        insurance.setPatient(patient); // bidirectional consistency maintenance

        return patient;
    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(null);
        return patient;
    }

}
