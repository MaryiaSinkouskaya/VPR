package com.vpr.app.controller;

import com.vpr.app.dto.request.AbnormalityRequestDto;
import com.vpr.app.dto.request.mappers.AbnormalityConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Abnormality;
import com.vpr.app.service.AbnormalityService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Abnormality", description = "List of probands abnormalities", externalDocs = @ExternalDocumentation(
        description = "About: what the abnormality is",
        url = "https://www.ncbi.nlm.nih.gov/books/NBK557691/"))
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/abnormality")
public class AbnormalityController {
    private final AbnormalityService abnormalityService;
    private final AbnormalityConverter abnormalityConverter;

    @GetMapping()
    public List<Abnormality> getAbnormalities() {
        return abnormalityService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Abnormality getAbnormalityById(@PathVariable(name = "id") long id) {
        return abnormalityService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Abnormality createAbnormality(@Validated(OnCreate.class) @RequestBody AbnormalityRequestDto abnormalityDto) {
        Abnormality abnormality = abnormalityConverter.toEntity(abnormalityDto);
        return abnormalityService.create(abnormality);
    }

    @PatchMapping()
    public Abnormality updateAbnormality(@Validated(OnUpdate.class) @RequestBody AbnormalityRequestDto abnormalityDto) {
        Abnormality abnormality = abnormalityConverter.toEntity(abnormalityDto);
        return abnormalityService.update(abnormality);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAbnormalityById(@PathVariable(name = "id") long id) {
        abnormalityService.delete(id);
    }
}
