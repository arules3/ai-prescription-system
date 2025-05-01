package com.aihealth.prescription_system.dto;

public record ChangePasswordRequest(String email ,String currentPassword , String newPassword) {
}
