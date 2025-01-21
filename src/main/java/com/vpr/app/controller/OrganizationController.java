package com.vpr.app.controller;

import com.vpr.app.dto.request.OrganizationRequestDto;
import com.vpr.app.entity.Organization;
import com.vpr.app.service.OrganizationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Organization", description = "API for accessing Hospital info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping()
    public List<Organization> getOrganizations() {
        return organizationService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Organization getOrganizationById(@PathVariable(name = "id") long id) {
        return organizationService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Organization createOrganization(@Valid @RequestBody OrganizationRequestDto organizationDto) {
        Organization organization = Organization.builder().number(organizationDto.getNumber()).name(organizationDto.getName()).build();
        return organizationService.create(organization);
    }

    @PatchMapping()
    public Organization updateOrganization(@Valid @RequestBody OrganizationRequestDto organizationDto) {
        Organization organization = Organization.builder().id(organizationDto.getId()).number(organizationDto.getNumber()).name(organizationDto.getName()).build();
        return organizationService.update(organization);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrganizationById(@PathVariable(name = "id") long id) {
        organizationService.delete(id);
    }
}
