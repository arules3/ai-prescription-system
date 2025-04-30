package com.aihealth.prescription_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.Setter;


@Data
@Builder
public class DoctorRegisterRequest {

    @NotBlank
    private String fullName;

    @Email
    private String email;

    @NotBlank
    private String password;


    private  String specialization;

    private String registrationNumber;
}
