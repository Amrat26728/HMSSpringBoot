package com.example.amrat.HMSSpringBoot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    // no need to define cascade
    // because we don't want that if an appointment is deleted then patient is also be deleted
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false) // owning side
    @ToString.Exclude
    private Patient patient;


    // no need to define cascade
    // because we don't want that if an appointment is deleted then doctor is also be deleted
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false) // owning side
    @ToString.Exclude
    private Doctor doctor;
}
