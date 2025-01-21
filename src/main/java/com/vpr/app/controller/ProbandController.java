package com.vpr.app.controller;

import com.vpr.app.dto.request.ProbandRequestDto;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.*;
import com.vpr.app.service.ProbandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Proband", description = "API for accessing data of probands")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/proband")
public class ProbandController {
    private final ProbandService probandService;

    @GetMapping()
    public List<Proband> getProbands() {
        return probandService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Proband getProbandById(@PathVariable(name = "id") long id) {
        return probandService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Proband createProband(@Validated(OnCreate.class) @RequestBody ProbandRequestDto probandDto) {
        Proband proband = Proband.builder()
                .birthDate(probandDto.getBirthDate())
                .karyotype(probandDto.getKaryotype())
                .pregnancyDurationInWeeks(probandDto.getPregnancyDurationInWeeks())
                .weight(probandDto.getWeight())
                .head(probandDto.getHead())
                .pregnancyNumber(probandDto.getPregnancyNumber())
                .isAborted(probandDto.isAborted())
                .laborOutcome(probandDto.getLaborOutcome())
                .gender(probandDto.getGender())
                .ploid(probandDto.getPloid())
                .note(Note.builder()
                        .date(probandDto.getNote().getDate())
                        .note(probandDto.getNote().getNote())
                        .build())
                .father(PersonInfo.builder()
                        .name(probandDto.getFather().getName())
                        .surname(probandDto.getFather().getSurname())
                        .patronymic(probandDto.getFather().getPatronymic())
                        .phone(probandDto.getFather().getPhone())
                        .birthDate(probandDto.getFather().getBirthDate())
                        .address(Address.builder()
                                .town(probandDto.getFather().getAddress().getTown())
                                .street(probandDto.getFather().getAddress().getStreet())
                                .building(probandDto.getFather().getAddress().getBuilding())
                                .apartment(probandDto.getFather().getAddress().getApartment())
                                .build())
                        .workplace(Workplace.builder()
                                .jobType(probandDto.getFather().getWorkplace().getJobType())
                                .company(probandDto.getFather().getWorkplace().getCompany())
                                .build())
                        .build())
                .mother(Mother.builder()
                        .lastMenstruationDate(probandDto.getMother().getLastMenstruationDate())
                        .diagnoseDate(probandDto.getMother().getDiagnoseDate())
                        .girlSurname(probandDto.getMother().getGirlSurname())
                        .personInfo(PersonInfo.builder()
                                .name(probandDto.getMother().getPersonInfo().getName())
                                .surname(probandDto.getMother().getPersonInfo().getSurname())
                                .patronymic(probandDto.getMother().getPersonInfo().getPatronymic())
                                .phone(probandDto.getMother().getPersonInfo().getPhone())
                                .birthDate(probandDto.getMother().getPersonInfo().getBirthDate())
                                .address(Address.builder()
                                        .town(probandDto.getMother().getPersonInfo().getAddress().getTown())
                                        .street(probandDto.getMother().getPersonInfo().getAddress().getStreet())
                                        .building(probandDto.getMother().getPersonInfo().getAddress().getBuilding())
                                        .apartment(probandDto.getMother().getPersonInfo().getAddress().getApartment())
                                        .build())
                                .workplace(Workplace.builder()
                                        .jobType(probandDto.getMother().getPersonInfo().getWorkplace().getJobType())
                                        .company(probandDto.getMother().getPersonInfo().getWorkplace().getCompany())
                                        .build())
                                .build())
                        .build())
                .organization(Organization.builder()
                        .number(probandDto.getOrganization().getNumber())
                        .name(probandDto.getOrganization().getName())
                        .build())
                .abnormality(Abnormality.builder().name(probandDto.getAbnormality().getName()).build())
                .build();
        return probandService.create(proband);
    }

    @PatchMapping()
    public Proband updateProband(@Validated(OnUpdate.class) @RequestBody ProbandRequestDto probandDto) {
        Proband proband = Proband.builder()
                .id(probandDto.getId())
                .birthDate(probandDto.getBirthDate())
                .karyotype(probandDto.getKaryotype())
                .pregnancyDurationInWeeks(probandDto.getPregnancyDurationInWeeks())
                .weight(probandDto.getWeight())
                .head(probandDto.getHead())
                .pregnancyNumber(probandDto.getPregnancyNumber())
                .isAborted(probandDto.isAborted())
                .laborOutcome(probandDto.getLaborOutcome())
                .gender(probandDto.getGender())
                .ploid(probandDto.getPloid())
                .note(Note.builder()
                        .id(probandDto.getNote().getId())
                        .date(probandDto.getNote().getDate())
                        .note(probandDto.getNote().getNote())
                        .build())
                .father(PersonInfo.builder()
                        .id(probandDto.getFather().getId())
                        .name(probandDto.getFather().getName())
                        .surname(probandDto.getFather().getSurname())
                        .patronymic(probandDto.getFather().getPatronymic())
                        .phone(probandDto.getFather().getPhone())
                        .birthDate(probandDto.getFather().getBirthDate())
                        .address(Address.builder()
                                .id(probandDto.getFather().getAddress().getId())
                                .town(probandDto.getFather().getAddress().getTown())
                                .street(probandDto.getFather().getAddress().getStreet())
                                .building(probandDto.getFather().getAddress().getBuilding())
                                .apartment(probandDto.getFather().getAddress().getApartment())
                                .build())
                        .workplace(Workplace.builder()
                                .id(probandDto.getFather().getWorkplace().getId())
                                .jobType(probandDto.getFather().getWorkplace().getJobType())
                                .company(probandDto.getFather().getWorkplace().getCompany())
                                .build())
                        .build())
                .mother(Mother.builder()
                        .id(probandDto.getMother().getId())
                        .lastMenstruationDate(probandDto.getMother().getLastMenstruationDate())
                        .diagnoseDate(probandDto.getMother().getDiagnoseDate())
                        .girlSurname(probandDto.getMother().getGirlSurname())
                        .personInfo(PersonInfo.builder()
                                .id(probandDto.getMother().getPersonInfo().getId())
                                .name(probandDto.getMother().getPersonInfo().getName())
                                .surname(probandDto.getMother().getPersonInfo().getSurname())
                                .patronymic(probandDto.getMother().getPersonInfo().getPatronymic())
                                .phone(probandDto.getMother().getPersonInfo().getPhone())
                                .birthDate(probandDto.getMother().getPersonInfo().getBirthDate())
                                .address(Address.builder()
                                        .id(probandDto.getMother().getPersonInfo().getAddress().getId())
                                        .town(probandDto.getMother().getPersonInfo().getAddress().getTown())
                                        .street(probandDto.getMother().getPersonInfo().getAddress().getStreet())
                                        .building(probandDto.getMother().getPersonInfo().getAddress().getBuilding())
                                        .apartment(probandDto.getMother().getPersonInfo().getAddress().getApartment())
                                        .build())
                                .workplace(Workplace.builder()
                                        .id(probandDto.getMother().getPersonInfo().getWorkplace().getId())
                                        .jobType(probandDto.getMother().getPersonInfo().getWorkplace().getJobType())
                                        .company(probandDto.getMother().getPersonInfo().getWorkplace().getCompany())
                                        .build())
                                .build())
                        .build())
                .organization(Organization.builder()
                        .id(probandDto.getOrganization().getId())
                        .number(probandDto.getOrganization().getNumber())
                        .name(probandDto.getOrganization().getName())
                        .build())
                .abnormality(Abnormality.builder().id(probandDto.getAbnormality().getId()).name(probandDto.getAbnormality().getName()).build())
                .build();
        return probandService.update(proband);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProbandById(@PathVariable(name = "id") long id) {
        probandService.delete(id);
    }
}
