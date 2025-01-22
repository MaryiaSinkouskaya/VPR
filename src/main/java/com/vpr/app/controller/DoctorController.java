package com.vpr.app.controller;

import com.vpr.app.dto.request.DoctorRequestDto;
import com.vpr.app.dto.request.mappers.DoctorConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Doctor;
import com.vpr.app.service.DoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Doctor", description = "API for accessing the doc data")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorConverter doctorConverter;

    @GetMapping()
    public List<Doctor> getDoctors() {
        return doctorService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Doctor getDoctorById(@PathVariable(name = "id") long id) {
        return doctorService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Doctor createDoctor(@Validated(OnCreate.class) @RequestBody DoctorRequestDto doctorDto) {
        Doctor doctor = doctorConverter.toEntity(doctorDto);
        return doctorService.create(doctor);
    }

    @PatchMapping()
    public Doctor updateDoctor(@Validated(OnUpdate.class) @RequestBody DoctorRequestDto doctorDto) {
        Doctor doctor = doctorConverter.toEntity(doctorDto);
        return doctorService.update(doctor);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDoctorById(@PathVariable(name = "id") long id) {
        doctorService.delete(id);
    }
}
