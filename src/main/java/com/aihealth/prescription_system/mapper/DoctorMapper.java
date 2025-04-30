package com.aihealth.prescription_system.mapper;

import com.aihealth.prescription_system.dto.DoctorRegisterRequest;
import com.aihealth.prescription_system.dto.DoctorResponse;
import com.aihealth.prescription_system.dto.DoctorUpdateRequest;
import com.aihealth.prescription_system.entity.Doctor;

import javax.print.Doc;

public class DoctorMapper {

    // For new doctor registration (no ID)
    public static Doctor toEntity(DoctorRegisterRequest doctorRegisterRequest , String encodePassword) {
        return Doctor.builder()
                .fullName(doctorRegisterRequest.getFullName())
                .email(doctorRegisterRequest.getEmail())
                .password(encodePassword)
                .specialization(doctorRegisterRequest.getSpecialization())
                .registrationNumber(doctorRegisterRequest.getRegistrationNumber())
                .build();

    }


    // For updating existing doctor
    public static Doctor toEntity(DoctorUpdateRequest request, String encodedPassword) {
        return Doctor.builder()
                .id(request.getId())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(encodedPassword)
                .specialization(request.getSpecialization())
                .registrationNumber(request.getRegistrationNumber())
                .build();
    }

    public static DoctorResponse toResponse(Doctor doctor) {
        return  new DoctorResponse(
                doctor.getId(),
                doctor.getFullName(),
                doctor.getEmail(),
                doctor.getSpecialization(),
                doctor.getRegistrationNumber()
        );
    }
}
