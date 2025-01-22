package com.vpr.app.controller;

import com.vpr.app.dto.request.ProbandDRequestDto;
import com.vpr.app.dto.request.mappers.ProbandDConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.ProbandD;
import com.vpr.app.service.ProbandDService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Dead proband", description = "API for accessing dead probands info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/probandD")
public class ProbandDController {
    private final ProbandDService probandDService;
    private final ProbandDConverter probandDConverter;

    @GetMapping()
    public List<ProbandD> getProbandDs() {
        return probandDService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ProbandD getProbandDById(@PathVariable(name = "id") long id) {
        return probandDService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProbandD createProbandD(@Validated(OnCreate.class) @RequestBody ProbandDRequestDto probandDDto) {
        ProbandD probandD = probandDConverter.toEntity(probandDDto);
        return probandDService.create(probandD);
    }

    @PatchMapping()
    public ProbandD updateProbandD(@Validated(OnUpdate.class) @RequestBody ProbandDRequestDto probandDDto) {
        ProbandD probandD = probandDConverter.toEntity(probandDDto);
        return probandDService.update(probandD);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProbandDById(@PathVariable(name = "id") long id) {
        probandDService.delete(id);
    }
}
