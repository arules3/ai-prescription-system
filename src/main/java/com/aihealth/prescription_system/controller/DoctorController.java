package com.aihealth.prescription_system.controller;


import com.aihealth.prescription_system.dto.ChangePasswordRequest;
import com.aihealth.prescription_system.dto.DoctorRegisterRequest;
import com.aihealth.prescription_system.dto.DoctorResponse;
import com.aihealth.prescription_system.dto.DoctorUpdateRequest;
import com.aihealth.prescription_system.entity.Doctor;
import com.aihealth.prescription_system.mapper.DoctorMapper;
import com.aihealth.prescription_system.repository.DoctorRepository;
import com.aihealth.prescription_system.security.DoctorDetails;
import com.aihealth.prescription_system.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<DoctorResponse> registerDoctor(@RequestBody @Valid DoctorRegisterRequest request) {
        DoctorResponse response =  doctorService.registerDoctor(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(@PathVariable Long id) {
        DoctorResponse response = doctorService.getDoctorById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public List<DoctorResponse> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return doctors.stream()
                .map(doctor -> new DoctorResponse(doctor.getId() , doctor.getFullName() , doctor.getEmail(), doctor.getSpecialization(), doctor.getSpecialization()))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public DoctorResponse updateDoctor( @PathVariable long id , @RequestBody DoctorUpdateRequest request )  {
        Doctor savedDoctor = doctorService.updateDoctor(id, request);
        return DoctorMapper.toResponse(savedDoctor);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword( @RequestBody ChangePasswordRequest request /*@AuthenticationPrincipal DoctorDetails doctorDetails*/ )  {
        doctorService.changePassword(request.email() ,request);
       //doctorService.changePassword(doctorDetails.getUsername() , request);
       return ResponseEntity.ok("Password changed successfully");

    }




}
