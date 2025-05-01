package com.aihealth.prescription_system.mapper;

import com.aihealth.prescription_system.dto.*;
import com.aihealth.prescription_system.entity.Doctor;

public class DoctorMapper {

    // Register DTO → Entity
    public static Doctor toEntity(DoctorRegisterRequest request, String encodedPassword) {
        return Doctor.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(encodedPassword)
                .specialization(request.specialization())
                .registrationNumber(request.registrationNumber())
                .build();
    }

    // Update existing Entity using UpdateRequest
    public static void updateEntity(Doctor existingDoctor, DoctorUpdateRequest request, String encodedPassword) {
        existingDoctor.setFullName(request.fullName());
        existingDoctor.setEmail(request.email());
        existingDoctor.setPassword(encodedPassword);
        existingDoctor.setSpecialization(request.specialization());
        existingDoctor.setRegistrationNumber(request.registrationNumber());
    }

    // Entity → Response DTO
    public static DoctorResponse toResponse(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getFullName(),
                doctor.getEmail(),
                doctor.getSpecialization(),
                doctor.getRegistrationNumber()
        );
    }
}
