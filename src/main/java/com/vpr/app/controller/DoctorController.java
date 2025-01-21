package com.vpr.app.controller;

import com.vpr.app.dto.request.DoctorRequestDto;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Address;
import com.vpr.app.entity.Doctor;
import com.vpr.app.entity.PersonInfo;
import com.vpr.app.entity.Workplace;
import com.vpr.app.service.DoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
        Doctor doctor = Doctor.builder()
                .speciality(doctorDto.getSpeciality())
                .personInfo(PersonInfo.builder()
                        .name(doctorDto.getPersonInfo().getName())
                        .surname(doctorDto.getPersonInfo().getSurname())
                        .patronymic(doctorDto.getPersonInfo().getPatronymic())
                        .phone(doctorDto.getPersonInfo().getPhone())
                        .birthDate(doctorDto.getPersonInfo().getBirthDate())
                        .address(Address.builder()
                                .town(doctorDto.getPersonInfo().getAddress().getTown())
                                .street(doctorDto.getPersonInfo().getAddress().getStreet())
                                .building(doctorDto.getPersonInfo().getAddress().getBuilding())
                                .apartment(doctorDto.getPersonInfo().getAddress().getApartment())
                                .build())
                        .workplace(Workplace.builder()
                                .jobType(doctorDto.getPersonInfo().getWorkplace().getJobType())
                                .company(doctorDto.getPersonInfo().getWorkplace().getCompany())
                                .build())
                        .build())
                .build();
        return doctorService.create(doctor);
    }

    @PatchMapping()
    public Doctor updateDoctor(@Validated(OnUpdate.class)@RequestBody DoctorRequestDto doctorDto) {
        Doctor doctor = Doctor.builder()
            .id(doctorDto.getId())
            .speciality(doctorDto.getSpeciality())
            .personInfo(PersonInfo.builder()
                .id(doctorDto.getPersonInfo().getId())
                .name(doctorDto.getPersonInfo().getName())
                .surname(doctorDto.getPersonInfo().getSurname())
                .patronymic(doctorDto.getPersonInfo().getPatronymic())
                .phone(doctorDto.getPersonInfo().getPhone())
                .birthDate(doctorDto.getPersonInfo().getBirthDate())
                .address(Address.builder()
                    .id(doctorDto.getPersonInfo().getAddress().getId())
                    .town(doctorDto.getPersonInfo().getAddress().getTown())
                    .street(doctorDto.getPersonInfo().getAddress().getStreet())
                    .building(doctorDto.getPersonInfo().getAddress().getBuilding())
                    .apartment(doctorDto.getPersonInfo().getAddress().getApartment())
                    .build())
                .workplace(Workplace.builder()
                    .id(doctorDto.getPersonInfo().getWorkplace().getId())
                    .jobType(doctorDto.getPersonInfo().getWorkplace().getJobType())
                    .company(doctorDto.getPersonInfo().getWorkplace().getCompany())
                    .build())
                .build())
            .build();
        return doctorService.update(doctor);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDoctorById(@PathVariable(name = "id") long id) {
        doctorService.delete(id);
    }
}
