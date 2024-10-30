package com.vpr.app.controller;

import com.vpr.app.entity.ProbandD;
import com.vpr.app.service.ProbandDService;
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
  public ProbandD createProbandD(@RequestBody ProbandD probandD) {
    return probandDService.create(probandD);
  }

  @PutMapping()
  public ProbandD updateProbandD(@RequestBody ProbandD probandD) {
    return probandDService.update(probandD);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteProbandDById(@PathVariable(name = "id") long id) {
    probandDService.delete(id);
  }
}
