package com.vpr.app.controller;

import com.vpr.app.dto.request.ProbandRequestDto;
import com.vpr.app.dto.request.mappers.ProbandConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Proband;
import com.vpr.app.service.ProbandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Proband", description = "API for accessing data of probands")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/proband")
public class ProbandController {
    private final ProbandService probandService;
    private final ProbandConverter probandConverter;

    @GetMapping()
    public List<Proband> getProbands() {
        return probandService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Proband getProbandById(@PathVariable(name = "id") long id) {
        return probandService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Proband createProband(@Validated(OnCreate.class) @RequestBody ProbandRequestDto probandDto) {
        Proband proband = probandConverter.toEntity(probandDto);
        return probandService.create(proband);
    }

    @PatchMapping()
    public Proband updateProband(@Validated(OnUpdate.class) @RequestBody ProbandRequestDto probandDto) {
        Proband proband = probandConverter.toEntity(probandDto);
        return probandService.update(proband);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProbandById(@PathVariable(name = "id") long id) {
        probandService.delete(id);
    }
}
