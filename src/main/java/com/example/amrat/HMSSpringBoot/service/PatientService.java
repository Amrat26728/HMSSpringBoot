package com.example.amrat.HMSSpringBoot.service;

import com.example.amrat.HMSSpringBoot.entity.Patient;
import com.example.amrat.HMSSpringBoot.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientService {

    public final PatientRepository patientRepository;

    // if his method successfully run without any error then transaction will be commited otherwise all db changes made in this method will be rollback.
    // after adding @Transactional annotation data will be saved in PersistenceContext
    // before fetching data from db it will be checked in PersistenceContext
    // like when p2 try to fetch data then it won't get because p2 is also trying to fetch same data that p1 already fetched.
    // and p1 == p2 will print true b/c both are referencing same object
    // if there is change in data in PersistenceContext then it will update data in db
    @Transactional
    public Patient getPatientById(Long id){
        Patient p1 = patientRepository.findById(id).orElseThrow();
        Patient p2 = patientRepository.findById(id).orElseThrow();
        System.out.println(p1 == p2);
        p1.setName("Kailash");
        return p1;
    }

}
