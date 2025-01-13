package com.vpr.app.controller;

import com.vpr.app.entity.Proband;
import com.vpr.app.service.ProbandService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
