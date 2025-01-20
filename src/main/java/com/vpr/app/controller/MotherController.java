package com.vpr.app.controller;

import com.vpr.app.dto.request.MotherRequestDto;
import com.vpr.app.entity.*;
import com.vpr.app.service.MotherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Mother", description = "API for accessing probands mother info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mother")
public class MotherController {
  private final MotherService motherService;

  @GetMapping()
  public List<Mother> getMothers() {
    return motherService.findAll();
  }

  @GetMapping(value = "/{id}")
  public Mother getMotherById(@PathVariable(name = "id") long id) {
    return motherService.findById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mother createMother(@RequestBody MotherRequestDto motherDto) {
    Mother mother = Mother.builder()
            .lastMenstruationDate(motherDto.getLastMenstruationDate())
            .diagnoseDate(motherDto.getDiagnoseDate())
            .girlSurname(motherDto.getGirlSurname())
            .personInfo(PersonInfo.builder()
                    .name(motherDto.getPersonInfo().getName())
                    .surname(motherDto.getPersonInfo().getSurname())
                    .patronymic(motherDto.getPersonInfo().getPatronymic())
                    .phone(motherDto.getPersonInfo().getPhone())
                    .birthDate(motherDto.getPersonInfo().getBirthDate())
                    .address(Address.builder()
                            .town(motherDto.getPersonInfo().getAddress().getTown())
                            .street(motherDto.getPersonInfo().getAddress().getStreet())
                            .building(motherDto.getPersonInfo().getAddress().getBuilding())
                            .apartment(motherDto.getPersonInfo().getAddress().getApartment())
                            .build())
                    .workplace(Workplace.builder()
                            .jobType(motherDto.getPersonInfo().getWorkplace().getJobType())
                            .company(motherDto.getPersonInfo().getWorkplace().getCompany())
                            .build())
                    .build())
            .build();
    return motherService.create(mother);
  }

  @PatchMapping()
  public Mother updateMother(@RequestBody Mother mother) {
    return motherService.update(mother);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteMotherById(@PathVariable(name = "id") long id) {
    motherService.delete(id);
  }
}
