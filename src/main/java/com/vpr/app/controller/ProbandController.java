package com.vpr.app.controller;

import com.vpr.app.entity.Proband;
import com.vpr.app.enums.Gender;
import com.vpr.app.service.ProbandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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

  @GetMapping(value = "/gender/{gender}")
  public int countProbandsByGender(@PathVariable(name = "gender") String gender) {
    return probandService.countByGender(Gender.valueOf(gender));
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Proband createProband(@RequestBody Proband proband) {
    return probandService.create(proband);
  }

  @PutMapping()
  public Proband updateProband(@RequestBody Proband proband) {
    return probandService.update(proband);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteProbandById(@PathVariable(name = "id") long id) {
    probandService.delete(id);
  }
}
