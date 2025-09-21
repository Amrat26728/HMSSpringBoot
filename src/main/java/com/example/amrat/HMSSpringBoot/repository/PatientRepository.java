package com.example.amrat.HMSSpringBoot.repository;

import com.example.amrat.HMSSpringBoot.dto.BloodGroupCountResponseEntity;
import com.example.amrat.HMSSpringBoot.entity.Patient;
import com.example.amrat.HMSSpringBoot.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    ///////// JPA Methods
    Patient findByName(String name);
    Patient findByDateOfBirth(LocalDate dateOfBirth);
//    Patient findByDateOfBirthOrEmail(LocalDate dateOfBirth, String email);
    List<Patient> findByDateOfBirthOrEmail(LocalDate dateOfBirth, String email);

    List<Patient> findByDateOfBirthBetween(LocalDate date1, LocalDate date2);

    List<Patient> findByNameContaining(String query);


    /////////// JPQL (Jakarta Persistence Query Language)
    @Query("SELECT p FROM Patient p WHERE p.bloodGroup=?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("SELECT p FROM Patient p WHERE p.dateOfBirth > :dateOfBirth")
    List<Patient> findByBornAfterDate(@Param("dateOfBirth") LocalDate dateOfBirth);

//    @Query("SELECT p.bloodGroup, Count(p) FROM Patient p GROUP BY p.bloodGroup")
//    List<Object[]> countEachBloodGroupType();

    /////////
    // Projection: projection only works with JPQL not with native query.
    @Query("SELECT new com.example.amrat.HMSSpringBoot.dto.BloodGroupCountResponseEntity(p.bloodGroup, Count(p)) FROM Patient p GROUP BY p.bloodGroup")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    //update query
    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name= :name WHERE p.id= :id")
    int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);

    ///////// Native Query
    @Query(value = "SELECT * FROM patient", nativeQuery = true)
//    List<Patient> findAllPatients();
    Page<Patient> findAllPatients(Pageable pageable);


}
