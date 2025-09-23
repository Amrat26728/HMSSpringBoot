package com.example.amrat.HMSSpringBoot;

import com.example.amrat.HMSSpringBoot.dto.BloodGroupCountResponseEntity;
import com.example.amrat.HMSSpringBoot.entity.Patient;
import com.example.amrat.HMSSpringBoot.entity.type.BloodGroupType;
import com.example.amrat.HMSSpringBoot.repository.PatientRepository;
import com.example.amrat.HMSSpringBoot.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        // for each patient it will get appointments also
        // so there are 5 patients and for each patient one extra query will execute to get appointments of that patient
        // this is called N+1 problem
        // solution: don't get appoints while fetching patient for that set FetchType.Lazy
        // in case you are returning JSON then add decorator @Json.Ignore
//        List<Patient> patientList = patientRepository.findAll();

        List<Patient> patientList = patientRepository.findAllPatientWithAppointments();
        for(Patient p: patientList){
            System.out.println(p);
        }
    }

    @Test
    public void testTransactionMethods(){

        ////////// JPA Methods
//        Patient patient = patientService.getPatientById(1L);
//        Patient patient = patientRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Patient not found"));
//        Patient patient = patientRepository.findByName("Ibad");
//        List<Patient> patients = patientRepository.findByDateOfBirthOrEmail(LocalDate.of(2000, 9, 20), "kailash@gmail.com");
//        List<Patient> patients = patientRepository.findByNameContaining("A");


        /////////// JPQL
//        List<Patient> patients = patientRepository.findByBloodGroup(BloodGroupType.B_POSITIVE);
//        List<Patient> patients = patientRepository.findByBornAfterDate(LocalDate.of(1998, 8, 20));
//        List<Patient> patients = patientRepository.findAllPatients();

//        System.out.println(patientRepository.updatePatientNameWithId("Loru", 5L));
//
//        for(Patient p: patients){
//            System.out.println(p);
//        }
//
//        List<BloodGroupCountResponseEntity> bloodGroupList = patientRepository.countEachBloodGroupType();
//        for(BloodGroupCountResponseEntity bloodGroupCountResponse: bloodGroupList){
//            System.out.println(bloodGroupCountResponse);
//        }

        ////// Pagination
//        Page<Patient> patients = patientRepository.findAllPatients(PageRequest.of(1, 2));
        /////// first it will sort by name and then get data from sorted.
        Page<Patient> patients = patientRepository.findAllPatients(PageRequest.of(1, 2, Sort.by("name")));

        System.out.println(patients.stream().count());
        for(Patient p: patients){
            System.out.println(p);
        }

    }

}
