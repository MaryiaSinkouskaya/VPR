package com.vpr.app.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.dto.request.OrganizationRequestDto;
import com.vpr.app.dto.request.mappers.OrganizationConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Organization;
import com.vpr.app.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Organization", description = "API for accessing Hospital info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
  private final OrganizationService organizationService;
  private final OrganizationConverter organizationConverter;

  /**
   * Retrieves all organizations.
   *
   * @return a list of all organizations
   */
  @GetMapping
  @Operation(summary = "Get all organizations", description = "Retrieves a list of all hospitals or organizations.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of organizations")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<List<Organization>> getOrganizations() {
    List<Organization> organizations = organizationService.findAll();
    return ResponseEntity.ok(organizations);
  }

  /**
   * Retrieves an organization by its ID.
   *
   * @param id the ID of the organization
   *
   * @return the organization with the specified ID
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get an organization by ID", description = "Retrieves the organization with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the organization")
  @ApiResponse(responseCode = "404", description = "Organization not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<Organization> getOrganizationById(@PathVariable(name = "id") long id) {
    Organization organization = organizationService.findById(id);
    return ResponseEntity.ok(organization);
  }

  /**
   * Creates a new organization.
   *
   * @param organizationDto the DTO for the organization to create
   *
   * @return the created organization
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new organization", description = "Creates a new hospital or organization with the provided details.")
  @ApiResponse(responseCode = "201", description = "Organization successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Organization> createOrganization(
      @Validated(OnCreate.class) @RequestBody OrganizationRequestDto organizationDto) {
    Organization organization = organizationConverter.toEntity(organizationDto);
    Organization createdOrganization = organizationService.create(organization);
    return ResponseEntity.status(CREATED).body(createdOrganization);
  }

  /**
   * Updates an existing organization.
   *
   * @param organizationDto the DTO for the organization to update
   *
   * @return the updated organization
   */
  @PatchMapping
  @Operation(summary = "Update an organization", description = "Updates an existing hospital or organization with the provided details.")
  @ApiResponse(responseCode = "200", description = "Organization successfully updated")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @ApiResponse(responseCode = "404", description = "Organization not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Organization> updateOrganization(
      @Validated(OnUpdate.class) @RequestBody OrganizationRequestDto organizationDto) {
    Organization organization = organizationConverter.toEntity(organizationDto);
    Organization updatedOrganization = organizationService.update(organization);
    return ResponseEntity.ok(updatedOrganization);
  }

  /**
   * Deletes an organization by its ID.
   *
   * @param id the ID of the organization to delete
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete an organization", description = "Deletes the hospital or organization with the specified ID.")
  @ApiResponse(responseCode = "204", description = "Organization successfully deleted")
  @ApiResponse(responseCode = "404", description = "Organization not found")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteOrganizationById(@PathVariable(name = "id") long id) {
    organizationService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

