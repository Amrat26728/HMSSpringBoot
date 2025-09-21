package com.example.amrat.HMSSpringBoot.entity;

import com.example.amrat.HMSSpringBoot.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Table(
        uniqueConstraints = {
//                @UniqueConstraint(name = "unique_patient_email", columnNames = {"email"}),
                @UniqueConstraint(name = "unique_patient_name_dob", columnNames = {"name", "dateOfBirth"}),
        },
        indexes = {
                @Index(name = "index_patient_birth_date", columnList = "dateOfBirth")
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(unique = true)
    private String email;

//    @ToString.Exclude
    private LocalDate dateOfBirth;

    private String gender;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @Column(updatable = false) // optional
    @CreationTimestamp // createdAt can't be updated
    private LocalDateTime createdAt;
}
