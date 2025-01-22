package com.vpr.app.controller;

import com.vpr.app.dto.request.MotherRequestDto;
import com.vpr.app.dto.request.mappers.MotherConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Mother;
import com.vpr.app.service.MotherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mother", description = "API for accessing probands mother info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mother")
public class MotherController {
    private final MotherService motherService;
    private final MotherConverter motherConverter;

    @GetMapping()
    public List<Mother> getMothers() {
        return motherService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mother getMotherById(@PathVariable(name = "id") long id) {
        return motherService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mother createMother(@Validated(OnCreate.class) @RequestBody MotherRequestDto motherDto) {
        Mother mother = motherConverter.toEntity(motherDto);
        return motherService.create(mother);
    }

    @PatchMapping()
    public Mother updateMother(@Validated(OnUpdate.class) @RequestBody MotherRequestDto motherDto) {
        Mother mother = motherConverter.toEntity(motherDto);
        return motherService.update(mother);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMotherById(@PathVariable(name = "id") long id) {
        motherService.delete(id);
    }
}
