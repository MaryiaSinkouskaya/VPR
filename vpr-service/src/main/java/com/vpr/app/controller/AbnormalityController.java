package com.vpr.app.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.dto.request.AbnormalityRequestDto;
import com.vpr.app.dto.request.mappers.AbnormalityConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Abnormality;
import com.vpr.app.service.AbnormalityService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
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

@Tag(name = "Abnormality", description = "List of probands abnormalities", externalDocs = @ExternalDocumentation(
    description = "About: what the abnormality is",
    url = "https://www.ncbi.nlm.nih.gov/books/NBK557691/"))
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/abnormality")
public class AbnormalityController {
  private final AbnormalityService abnormalityService;
  private final AbnormalityConverter abnormalityConverter;

  /**
   * Retrieves all abnormalities.
   *
   * @return ResponseEntity with a list of abnormalities
   */
  @GetMapping
  @Operation(summary = "Get all abnormalities", description = "Retrieves a list of all abnormalities.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of abnormalities")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<List<Abnormality>> getAbnormalities() {
    List<Abnormality> abnormalities = abnormalityService.findAll();
    return ResponseEntity.ok(abnormalities);
  }

  /**
   * Retrieves an abnormality by its ID.
   *
   * @param id the ID of the abnormality
   *
   * @return ResponseEntity with the abnormality or a 404 error if not found
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get an abnormality by ID", description = "Retrieves the abnormality with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the abnormality")
  @ApiResponse(responseCode = "404", description = "Abnormality not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<Abnormality> getAbnormalityById(
      @PathVariable(name = "id") long id) {
    Abnormality abnormality = abnormalityService.findById(id);
    return ResponseEntity.ok(abnormality);
  }

  /**
   * Creates a new abnormality.
   *
   * @param abnormalityDto the DTO for the abnormality to create
   *
   * @return ResponseEntity with the created abnormality
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new abnormality", description = "Creates a new abnormality with the provided details.")
  @ApiResponse(responseCode = "201", description = "Successfully created the abnormality")
  @ApiResponse(responseCode = "400", description = "Invalid input data")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Abnormality> createAbnormality(
      @Validated(OnCreate.class) @RequestBody AbnormalityRequestDto abnormalityDto) {
    Abnormality abnormality = abnormalityConverter.toEntity(abnormalityDto);
    Abnormality createdAbnormality = abnormalityService.create(abnormality);
    return ResponseEntity.status(CREATED).body(createdAbnormality);
  }

  /**
   * Updates an existing abnormality.
   *
   * @param abnormalityDto the DTO for the abnormality to update
   *
   * @return ResponseEntity with the updated abnormality
   */
  @PatchMapping
  @Operation(summary = "Update an abnormality", description = "Updates an existing abnormality with the provided details.")
  @ApiResponse(responseCode = "200", description = "Successfully updated the abnormality")
  @ApiResponse(responseCode = "400", description = "Invalid input data")
  @ApiResponse(responseCode = "404", description = "Abnormality not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Abnormality> updateAbnormality(
      @Validated(OnUpdate.class) @RequestBody AbnormalityRequestDto abnormalityDto) {
    Abnormality abnormality = abnormalityConverter.toEntity(abnormalityDto);
    Abnormality updatedAbnormality = abnormalityService.update(abnormality);
    return ResponseEntity.ok(updatedAbnormality);
  }

  /**
   * Deletes an abnormality by its ID.
   *
   * @param id the ID of the abnormality to delete
   *
   * @return ResponseEntity with no content
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete an abnormality", description = "Deletes the abnormality with the specified ID.")

  @ApiResponse(responseCode = "204", description = "Successfully deleted the abnormality")
  @ApiResponse(responseCode = "404", description = "Abnormality not found")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteAbnormalityById(
      @PathVariable(name = "id") long id) {
    abnormalityService.delete(id);
    return ResponseEntity.noContent().build();
  }
}