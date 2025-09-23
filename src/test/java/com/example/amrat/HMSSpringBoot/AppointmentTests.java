package com.example.amrat.HMSSpringBoot;

import com.example.amrat.HMSSpringBoot.entity.Appointment;
import com.example.amrat.HMSSpringBoot.entity.Insurance;
import com.example.amrat.HMSSpringBoot.entity.Patient;
import com.example.amrat.HMSSpringBoot.service.AppointmentService;
import com.example.amrat.HMSSpringBoot.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTests {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void testCreateNewAppointment(){

        ///// create appointment
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 9, 29, 16, 30))
                .reason("appointment testing")
                .build();
        Appointment appoint = appointmentService.createNewAppointment(appointment, 1L, 1L);
        System.out.println("Created appointment: " + appoint);

        ////// reassign created appointment
        Appointment updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(1L, 2L);
        System.out.println("Re Assigned appointment: " + updatedAppointment);

    }

    @Test
    public void testInsurance(){
        ////// assign insurance
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_123")
                .provider("HDFC")
                .validUntil(LocalDate.of(2028, 12, 31))
                .build();
        Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(patient);

        /////// disassociate insurance
        Patient insuranceRemoved = insuranceService.disassociateInsuranceFromPatient(patient.getId());
        System.out.println(insuranceRemoved);
    }
}
