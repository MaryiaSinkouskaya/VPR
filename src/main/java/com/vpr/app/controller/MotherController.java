package com.vpr.app.controller;

import com.vpr.app.entity.Mother;
import com.vpr.app.service.MotherService;
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
  public Mother createMother(@RequestBody Mother mother) {
    return motherService.create(mother);
  }

  @PutMapping()
  public Mother updateMother(@RequestBody Mother mother) {
    return motherService.update(mother);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteMotherById(@PathVariable(name = "id") long id) {
    motherService.delete(id);
  }
}
