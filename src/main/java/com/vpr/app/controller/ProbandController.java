package com.vpr.app.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.dto.request.ProbandRequestDto;
import com.vpr.app.dto.request.mappers.ProbandConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Proband;
import com.vpr.app.service.ProbandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@Tag(name = "Proband", description = "API for accessing data of probands")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/proband")
public class ProbandController {
  private final ProbandService probandService;
  private final ProbandConverter probandConverter;

  @GetMapping
  @Operation(summary = "Get all probands", description = "Retrieves a list of all probands.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of probands")
  public ResponseEntity<List<Proband>> getProbands() {
    List<Proband> probands = probandService.findAll();
    return ResponseEntity.ok(probands);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a proband by ID", description = "Retrieves the proband with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the proband")
  @ApiResponse(responseCode = "404", description = "Proband not found")
  public ResponseEntity<Proband> getProbandById(@PathVariable(name = "id") long id) {
    Proband proband = probandService.findById(id);
    return ResponseEntity.ok(proband);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new proband", description = "Creates a new proband with the provided details.")
  @ApiResponse(responseCode = "201", description = "Proband successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  public ResponseEntity<Proband> createProband(
      @Validated(OnCreate.class) @RequestBody ProbandRequestDto probandDto) {
    Proband proband = probandConverter.toEntity(probandDto);
    Proband createdProband = probandService.create(proband);
    return ResponseEntity.status(CREATED).body(createdProband);
  }

  @PatchMapping
  @Operation(summary = "Update a proband", description = "Updates an existing proband with the provided details.")
  @ApiResponse(responseCode = "200", description = "Proband successfully updated")
  @ApiResponse(responseCode = "404", description = "Proband not found")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  public ResponseEntity<Proband> updateProband(
      @Validated(OnUpdate.class) @RequestBody ProbandRequestDto probandDto) {
    Proband proband = probandConverter.toEntity(probandDto);
    Proband updatedProband = probandService.update(proband);
    return ResponseEntity.ok(updatedProband);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a proband", description = "Deletes the proband with the specified ID.")
  @ApiResponse(responseCode = "204", description = "Proband successfully deleted")
  @ApiResponse(responseCode = "404", description = "Proband not found")
  public ResponseEntity<Void> deleteProbandById(@PathVariable(name = "id") long id) {
    probandService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

