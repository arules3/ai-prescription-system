package com.aihealth.prescription_system.dto;


import lombok.Data;

@Data
public class DoctorUpdateRequest {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String specialization;
    private String registrationNumber;
}
