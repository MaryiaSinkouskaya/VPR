package com.vpr.app.controller;

import com.vpr.app.dto.request.OrganizationRequestDto;
import com.vpr.app.dto.request.mappers.OrganizationConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Organization;
import com.vpr.app.service.OrganizationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Organization", description = "API for accessing Hospital info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final OrganizationConverter organizationConverter;

    @GetMapping()
    public List<Organization> getOrganizations() {
        return organizationService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Organization getOrganizationById(@PathVariable(name = "id") long id) {
        return organizationService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Organization createOrganization(@Validated(OnCreate.class) @RequestBody OrganizationRequestDto organizationDto) {
        Organization organization = organizationConverter.toEntity(organizationDto);
        return organizationService.create(organization);
    }

    @PatchMapping()
    public Organization updateOrganization(@Validated(OnUpdate.class) @RequestBody OrganizationRequestDto organizationDto) {
        Organization organization = organizationConverter.toEntity(organizationDto);
        return organizationService.update(organization);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrganizationById(@PathVariable(name = "id") long id) {
        organizationService.delete(id);
    }
}
