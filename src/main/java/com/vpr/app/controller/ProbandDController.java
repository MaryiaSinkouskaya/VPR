package com.vpr.app.controller;

import com.vpr.app.controller.dto.request.ProbandDRequestDto;
import com.vpr.app.entity.*;
import com.vpr.app.service.ProbandDService;
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

@Tag(name = "Dead proband", description = "API for accessing dead probands info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/probandD")
public class ProbandDController {
  private final ProbandDService probandDService;

  @GetMapping()
  public List<ProbandD> getProbandDs() {
    return probandDService.findAll();
  }

  @GetMapping(value = "/{id}")
  public ProbandD getProbandDById(@PathVariable(name = "id") long id) {
    return probandDService.findById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ProbandD createProbandD(@RequestBody ProbandDRequestDto probandDDto) {
    ProbandD probandD = ProbandD.builder()
            .deathDate(probandDDto.getDeathDate())
            .proband(Proband.builder()
                    .birthDate(probandDDto.getProband().getBirthDate())
                    .karyotype(probandDDto.getProband().getKaryotype())
                    .pregnancyDurationInWeeks(probandDDto.getProband().getPregnancyDurationInWeeks())
                    .weight(probandDDto.getProband().getWeight())
                    .head(probandDDto.getProband().getHead())
                    .pregnancyNumber(probandDDto.getProband().getPregnancyNumber())
                    .isAborted(probandDDto.getProband().isAborted())
                    .laborOutcome(probandDDto.getProband().getLaborOutcome())
                    .gender(probandDDto.getProband().getGender())
                    .ploid(probandDDto.getProband().getPloid())
                    .note(Note.builder()
                            .date(probandDDto.getProband().getNote().getDate())
                            .note(probandDDto.getProband().getNote().getNote())
                            .build())
                    .father(PersonInfo.builder()
                            .name(probandDDto.getProband().getFather().getName())
                            .surname(probandDDto.getProband().getFather().getSurname())
                            .patronymic(probandDDto.getProband().getFather().getPatronymic())
                            .phone(probandDDto.getProband().getFather().getPhone())
                            .birthDate(probandDDto.getProband().getFather().getBirthDate())
                            .address(Address.builder()
                                    .town(probandDDto.getProband().getFather().getAddress().getTown())
                                    .street(probandDDto.getProband().getFather().getAddress().getStreet())
                                    .building(probandDDto.getProband().getFather().getAddress().getBuilding())
                                    .apartment(probandDDto.getProband().getFather().getAddress().getApartment())
                                    .build())
                            .workplace(Workplace.builder()
                                    .jobType(probandDDto.getProband().getFather().getWorkplace().getJobType())
                                    .company(probandDDto.getProband().getFather().getWorkplace().getCompany())
                                    .build())
                            .build())
                    .mother(Mother.builder()
                            .lastMenstruationDate(probandDDto.getProband().getMother().getLastMenstruationDate())
                            .diagnoseDate(probandDDto.getProband().getMother().getDiagnoseDate())
                            .girlSurname(probandDDto.getProband().getMother().getGirlSurname())
                            .personInfo(PersonInfo.builder()
                                    .name(probandDDto.getProband().getMother().getPersonInfo().getName())
                                    .surname(probandDDto.getProband().getMother().getPersonInfo().getSurname())
                                    .patronymic(probandDDto.getProband().getMother().getPersonInfo().getPatronymic())
                                    .phone(probandDDto.getProband().getMother().getPersonInfo().getPhone())
                                    .birthDate(probandDDto.getProband().getMother().getPersonInfo().getBirthDate())
                                    .address(Address.builder()
                                            .town(probandDDto.getProband().getMother().getPersonInfo().getAddress().getTown())
                                            .street(probandDDto.getProband().getMother().getPersonInfo().getAddress().getStreet())
                                            .building(probandDDto.getProband().getMother().getPersonInfo().getAddress().getBuilding())
                                            .apartment(probandDDto.getProband().getMother().getPersonInfo().getAddress().getApartment())
                                            .build())
                                    .workplace(Workplace.builder()
                                            .jobType(probandDDto.getProband().getMother().getPersonInfo().getWorkplace().getJobType())
                                            .company(probandDDto.getProband().getMother().getPersonInfo().getWorkplace().getCompany())
                                            .build())
                                    .build())
                            .build())
                    .organization(Organization.builder()
                            .number(probandDDto.getProband().getOrganization().getNumber())
                            .name(probandDDto.getProband().getOrganization().getName())
                            .build())
                    .abnormality(Abnormality.builder().name(probandDDto.getProband().getAbnormality().getName()).build())
                    .build())
            .build();
    return probandDService.create(probandD);
  }

  @PatchMapping()
  public ProbandD updateProbandD(@RequestBody ProbandD probandD) {
    return probandDService.update(probandD);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteProbandDById(@PathVariable(name = "id") long id) {
    probandDService.delete(id);
  }
}
