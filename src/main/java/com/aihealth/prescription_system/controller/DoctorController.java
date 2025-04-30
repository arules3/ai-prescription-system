package com.aihealth.prescription_system.controller;


import com.aihealth.prescription_system.dto.DoctorRegisterRequest;
import com.aihealth.prescription_system.dto.DoctorResponse;
import com.aihealth.prescription_system.entity.Doctor;
import com.aihealth.prescription_system.repository.DoctorRepository;
import com.aihealth.prescription_system.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;


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




}
