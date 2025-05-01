package com.aihealth.prescription_system.dto;



public record DoctorRegisterRequest(String fullName,
                                    String email,
                                    String password,
                                    String specialization,
                                    String registrationNumber) {}

