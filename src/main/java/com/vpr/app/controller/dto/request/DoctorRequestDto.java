package com.vpr.app.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorRequestDto {

    @NotBlank(message = "Speciality must not be blank")
    @Size(max = 64, message = "Speciality must not exceed 64 characters")
    private String speciality;

    private PersonInfoRequestDto personInfo;
}
