package com.aihealth.prescription_system.dto;


public record DoctorUpdateRequest(Long id,
                                  String fullName,
                                  String email,
                                  String password,
                                  String specialization,
                                  String registrationNumber) {}
