package com.aihealth.prescription_system.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private Long id;
    private String fullName;
    private String email;
    private String specialization;
    private String registrationNumber;

}
