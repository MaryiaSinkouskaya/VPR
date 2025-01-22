package com.vpr.app.controller;

import com.vpr.app.dto.request.PersonInfoRequestDto;
import com.vpr.app.dto.request.mappers.PersonInfoConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.PersonInfo;
import com.vpr.app.service.PersonInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PersonInfo", description = "API for accessing person (proband's doctor, mother, father) info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/personInfo")
public class PersonInfoController {
    private final PersonInfoService personInfoService;
    private final PersonInfoConverter personInfoConverter;

    @GetMapping()
    public List<PersonInfo> getPersonInfos() {
        return personInfoService.findAll();
    }

    @GetMapping(value = "/{id}")
    public PersonInfo getPersonInfoById(@PathVariable(name = "id") long id) {
        return personInfoService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonInfo createPersonInfo(@Validated(OnCreate.class) @RequestBody PersonInfoRequestDto personInfoDto) {
        PersonInfo personInfo = personInfoConverter.toEntity(personInfoDto);
        return personInfoService.create(personInfo);
    }

    @PatchMapping()
    public PersonInfo updatePersonInfo(@Validated(OnUpdate.class) @RequestBody PersonInfoRequestDto personInfoDto) {
        PersonInfo personInfo = personInfoConverter.toEntity(personInfoDto);
        return personInfoService.update(personInfo);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePersonInfoById(@PathVariable(name = "id") long id) {
        personInfoService.delete(id);
    }
}
