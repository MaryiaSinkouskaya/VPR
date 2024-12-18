package com.vpr.app.controller;

import com.vpr.app.entity.Abnormality;
import com.vpr.app.service.AbnormalityService;
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
@RequestMapping("/api/abnormality")
public class AbnormalityController {
  private final AbnormalityService abnormalityService;

  @GetMapping()
  public List<Abnormality> getAbnormalities() {
    return abnormalityService.findAll();
  }

  @GetMapping(value = "/{id}")
  public Abnormality getAbnormalityById(@PathVariable(name = "id") long id) {
    return abnormalityService.findById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Abnormality createAbnormality(@RequestBody Abnormality abnormality) {
    return abnormalityService.create(abnormality);
  }

  @PutMapping()
  public Abnormality updateAbnormality(@RequestBody Abnormality abnormality) {
    return abnormalityService.update(abnormality);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteAbnormalityById(@PathVariable(name = "id") long id) {
    abnormalityService.delete(id);
  }
}
