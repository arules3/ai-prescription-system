package com.aihealth.prescription_system.service;

import com.aihealth.prescription_system.config.SecurityConfig;
import com.aihealth.prescription_system.dto.DoctorRegisterRequest;
import com.aihealth.prescription_system.dto.DoctorResponse;
import com.aihealth.prescription_system.dto.DoctorResponse;
import com.aihealth.prescription_system.entity.Doctor;
import com.aihealth.prescription_system.mapper.DoctorMapper;
import com.aihealth.prescription_system.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;


@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private SecurityConfig securityConfig;

    public DoctorResponse registerDoctor(DoctorRegisterRequest request) {

        // Use mapper to convert DTO to Entity
        Doctor doctor = DoctorMapper.toEntity(request, passwordEncoder.encode(request.getPassword()));
        // Save to DB
        Doctor savedDoctor = doctorRepository.save(doctor);
        return  DoctorMapper.toResponse(savedDoctor);

        /*Doctor doctor = Doctor.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(securityConfig.passwordEncoder().encode(request.getPassword()))
                .specialization(request.getSpecialization())
                .registrationNumber(request.getRegistrationNumber())
                .build();

        doctor =  doctorRepository.save(doctor);

        return new DoctorResponse(doctor.getId() , doctor.getFullName() , doctor.getEmail() , doctor.getSpecialization(), doctor.getRegistrationNumber());*/
    }

    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with the provided ID : " + id));

        /*return new DoctorResponse(doctor.getId() , doctor.getFullName(),
                doctor.getEmail() , doctor.getRegistrationNumber() , doctor.getSpecialization());*/

        return DoctorMapper.toResponse(doctor);
    }


}
