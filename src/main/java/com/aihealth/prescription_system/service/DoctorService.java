package com.aihealth.prescription_system.service;

import com.aihealth.prescription_system.config.SecurityConfig;
import com.aihealth.prescription_system.dto.*;
import com.aihealth.prescription_system.entity.Doctor;
import com.aihealth.prescription_system.mapper.DoctorMapper;
import com.aihealth.prescription_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityConfig securityConfig;

    public DoctorResponse registerDoctor(DoctorRegisterRequest request) {
        Doctor doctor = DoctorMapper.toEntity(request, passwordEncoder.encode(request.password()));
        Doctor savedDoctor = doctorRepository.save(doctor);
        return DoctorMapper.toResponse(savedDoctor);
    }

    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with the provided ID : " + id));
        return DoctorMapper.toResponse(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(long id, DoctorUpdateRequest request) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + id));

        String encodedPassword = passwordEncoder.encode(request.password());
        DoctorMapper.updateEntity(existingDoctor, request, encodedPassword);

        return doctorRepository.save(existingDoctor);
    }

    public void changePassword(String email, ChangePasswordRequest request) {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor with this email does not exist: " + email));

        if (!passwordEncoder.matches(request.currentPassword(), doctor.getPassword())) {
            throw new BadCredentialsException("Current password is not correct");
        }

        doctor.setPassword(passwordEncoder.encode(request.newPassword()));
        doctorRepository.save(doctor);
    }
}
