package com.vpr.app.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.dto.request.WorkplaceRequestDto;
import com.vpr.app.dto.request.mappers.WorkplaceConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Workplace;
import com.vpr.app.service.WorkplaceService;
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

@Tag(name = "Workplace", description = "API for accessing proband's parent workplace info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/workplace")
public class WorkplaceController {
  private final WorkplaceService workplaceService;
  private final WorkplaceConverter workplaceConverter;

  @GetMapping
  @Operation(summary = "Get all workplaces", description = "Retrieves a list of all workplaces.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of workplaces")
  public ResponseEntity<List<Workplace>> getWorks() {
    List<Workplace> workplaces = workplaceService.findAll();
    return ResponseEntity.ok(workplaces);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a workplace by ID", description = "Retrieves the workplace with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the workplace")
  @ApiResponse(responseCode = "404", description = "Workplace not found")
  public ResponseEntity<Workplace> getWorkById(@PathVariable(name = "id") long id) {
    Workplace workplace = workplaceService.findById(id);
    return ResponseEntity.ok(workplace);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new workplace", description = "Creates a new workplace with the provided details.")
  @ApiResponse(responseCode = "201", description = "Workplace successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  public ResponseEntity<Workplace> createWork(
      @Validated(OnCreate.class) @RequestBody WorkplaceRequestDto workplaceDto) {
    Workplace workplace = workplaceConverter.toEntity(workplaceDto);
    Workplace createdWorkplace = workplaceService.create(workplace);
    return ResponseEntity.status(CREATED).body(createdWorkplace);
  }

  @PatchMapping
  @Operation(summary = "Update a workplace", description = "Updates an existing workplace with the provided details.")
  @ApiResponse(responseCode = "200", description = "Workplace successfully updated")
  @ApiResponse(responseCode = "404", description = "Workplace not found")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  public ResponseEntity<Workplace> updateWork(
      @Validated(OnUpdate.class) @RequestBody WorkplaceRequestDto workplaceDto) {
    Workplace workplace = workplaceConverter.toEntity(workplaceDto);
    Workplace updatedWorkplace = workplaceService.update(workplace);
    return ResponseEntity.ok(updatedWorkplace);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a workplace", description = "Deletes the workplace with the specified ID.")
  @ApiResponse(responseCode = "204", description = "Workplace successfully deleted")
  @ApiResponse(responseCode = "404", description = "Workplace not found")
  public ResponseEntity<Void> deleteWorkById(@PathVariable(name = "id") long id) {
    workplaceService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
