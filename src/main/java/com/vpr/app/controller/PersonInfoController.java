package com.vpr.app.controller;

import com.vpr.app.entity.PersonInfo;
import com.vpr.app.service.PersonInfoService;
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

@Tag(name = "PersonInfo", description = "API for accessing person (proband's doctor, mother, father) info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/personInfo")
public class PersonInfoController {
  private final PersonInfoService personInfoService;

  @GetMapping()
  public List<PersonInfo> getPersonInfos() {
    return personInfoService.findAll();
  }

  @GetMapping(value = "/{id}")
  public PersonInfo getPersonInfoById(@PathVariable(name = "id") long id) {
    return personInfoService.findById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public PersonInfo createPersonInfo(@RequestBody PersonInfo personInfo) {
    return personInfoService.create(personInfo);
  }

  @PatchMapping()
  public PersonInfo updatePersonInfo(@RequestBody PersonInfo personInfo) {
    return personInfoService.update(personInfo);
  }

  @DeleteMapping(value = "/{id}")
  public void deletePersonInfoById(@PathVariable(name = "id") long id) {
    personInfoService.delete(id);
  }
}
