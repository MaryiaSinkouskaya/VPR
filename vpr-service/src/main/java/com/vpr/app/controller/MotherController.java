package com.vpr.app.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.dto.request.MotherRequestDto;
import com.vpr.app.dto.request.mappers.MotherConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Mother;
import com.vpr.app.service.MotherService;
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

@Tag(name = "Mother", description = "API for accessing probands' mother information")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mother")
public class MotherController {
  private final MotherService motherService;
  private final MotherConverter motherConverter;

  /**
   * Retrieves all mothers.
   *
   * @return a list of all mothers
   */
  @GetMapping
  @Operation(summary = "Get all mothers", description = "Retrieves a list of all mothers.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of mothers")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<List<Mother>> getMothers() {
    List<Mother> mothers = motherService.findAll();
    return ResponseEntity.ok(mothers);
  }

  /**
   * Retrieves a mother by its ID.
   *
   * @param id the ID of the mother
   *
   * @return the mother with the specified ID
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get a mother by ID", description = "Retrieves the mother with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the mother")
  @ApiResponse(responseCode = "404", description = "Mother not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<Mother> getMotherById(@PathVariable(name = "id") long id) {
    Mother mother = motherService.findById(id);
    return ResponseEntity.ok(mother);
  }

  /**
   * Creates a new mother.
   *
   * @param motherDto the DTO for the mother to create
   *
   * @return the created mother
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new mother", description = "Creates a new mother with the provided details.")
  @ApiResponse(responseCode = "201", description = "Mother successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Mother> createMother(
      @Validated(OnCreate.class) @RequestBody MotherRequestDto motherDto) {
    Mother mother = motherConverter.toEntity(motherDto);
    Mother createdMother = motherService.create(mother);
    return ResponseEntity.status(CREATED).body(createdMother);
  }

  /**
   * Updates an existing mother.
   *
   * @param motherDto the DTO for the mother to update
   *
   * @return the updated mother
   */
  @PatchMapping
  @Operation(summary = "Update a mother", description = "Updates an existing mother with the provided details.")
  @ApiResponse(responseCode = "200", description = "Mother successfully updated")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @ApiResponse(responseCode = "404", description = "Mother not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Mother> updateMother(
      @Validated(OnUpdate.class) @RequestBody MotherRequestDto motherDto) {
    Mother mother = motherConverter.toEntity(motherDto);
    Mother updatedMother = motherService.update(mother);
    return ResponseEntity.ok(updatedMother);
  }

  /**
   * Deletes a mother by its ID.
   *
   * @param id the ID of the mother to delete
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a mother", description = "Deletes the mother with the specified ID.")
  @ApiResponse(responseCode = "204", description = "Mother successfully deleted")
  @ApiResponse(responseCode = "404", description = "Mother not found")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteMotherById(@PathVariable(name = "id") long id) {
    motherService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
