package com.vpr.app.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.dto.request.ProbandDRequestDto;
import com.vpr.app.dto.request.mappers.ProbandDConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.ProbandD;
import com.vpr.app.service.ProbandDService;
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

@Tag(name = "Dead proband", description = "API for accessing dead probands info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/probandD")
public class ProbandDController {
  private final ProbandDService probandDService;
  private final ProbandDConverter probandDConverter;

  @GetMapping
  @Operation(summary = "Get all dead probands", description = "Retrieves a list of all dead probands.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of dead probands")
  public ResponseEntity<List<ProbandD>> getProbandDs() {
    List<ProbandD> probandDs = probandDService.findAll();
    return ResponseEntity.ok(probandDs);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a dead proband by ID", description = "Retrieves the dead proband with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the dead proband")
  @ApiResponse(responseCode = "404", description = "Dead proband not found")
  public ResponseEntity<ProbandD> getProbandDById(@PathVariable(name = "id") long id) {
    ProbandD probandD = probandDService.findById(id);
    return ResponseEntity.ok(probandD);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new dead proband", description = "Creates a new dead proband with the provided details.")
  @ApiResponse(responseCode = "201", description = "Dead proband successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  public ResponseEntity<ProbandD> createProbandD(
      @Validated(OnCreate.class) @RequestBody ProbandDRequestDto probandDDto) {
    ProbandD probandD = probandDConverter.toEntity(probandDDto);
    ProbandD createdProbandD = probandDService.create(probandD);
    return ResponseEntity.status(CREATED).body(createdProbandD);
  }

  @PatchMapping
  @Operation(summary = "Update a dead proband", description = "Updates an existing dead proband with the provided details.")
  @ApiResponse(responseCode = "200", description = "Dead proband successfully updated")
  @ApiResponse(responseCode = "404", description = "Dead proband not found")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  public ResponseEntity<ProbandD> updateProbandD(
      @Validated(OnUpdate.class) @RequestBody ProbandDRequestDto probandDDto) {
    ProbandD probandD = probandDConverter.toEntity(probandDDto);
    ProbandD updatedProbandD = probandDService.update(probandD);
    return ResponseEntity.ok(updatedProbandD);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a dead proband", description = "Deletes the dead proband with the specified ID.")
  @ApiResponse(responseCode = "204", description = "Dead proband successfully deleted")
  @ApiResponse(responseCode = "404", description = "Dead proband not found")
  public ResponseEntity<Void> deleteProbandDById(@PathVariable(name = "id") long id) {
    probandDService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
