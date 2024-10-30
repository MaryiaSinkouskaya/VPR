package com.vpr.app.controller;

import com.vpr.app.entity.Workplace;
import com.vpr.app.service.WorkplaceService;
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
@RequestMapping("/api/workplace")
public class WorkplaceController {
  private final WorkplaceService workplaceService;

  @GetMapping()
  public List<Workplace> getWorks() {
    return workplaceService.findAll();
  }

  @GetMapping(value = "/{id}")
  public Workplace getWorkById(@PathVariable(name = "id") long id) {
    return workplaceService.findById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Workplace createWork(@RequestBody Workplace workplace) {
    return workplaceService.create(workplace);
  }

  @PutMapping()
  public Workplace updateWork(@RequestBody Workplace workplace) {
    return workplaceService.update(workplace);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteWorkById(@PathVariable(name = "id") long id) {
    workplaceService.delete(id);
  }
}
