package com.aihealth.prescription_system.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String specialization;

    private String registrationNumber;


}
