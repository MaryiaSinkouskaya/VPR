package com.vpr.app.controller;

import com.vpr.app.dto.request.WorkplaceRequestDto;
import com.vpr.app.entity.Workplace;
import com.vpr.app.service.WorkplaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Workplace", description = "API for accessing proband's parent workplace info")
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
    public Workplace createWork(@Valid @RequestBody WorkplaceRequestDto workplaceDto) {
        Workplace workplace = Workplace.builder()
                .jobType(workplaceDto.getJobType())
                .company(workplaceDto.getCompany())
                .build();
        return workplaceService.create(workplace);
    }

    @PatchMapping()
    public Workplace updateWork(@Valid @RequestBody WorkplaceRequestDto workplaceDto) {
        Workplace workplace = Workplace.builder()
                .id(workplaceDto.getId())
                .jobType(workplaceDto.getJobType())
                .company(workplaceDto.getCompany())
                .build();
        return workplaceService.update(workplace);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteWorkById(@PathVariable(name = "id") long id) {
        workplaceService.delete(id);
    }
}
