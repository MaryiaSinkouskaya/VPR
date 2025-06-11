package com.vpr.app.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.dto.request.PersonInfoRequestDto;
import com.vpr.app.dto.request.mappers.PersonInfoConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.PersonInfo;
import com.vpr.app.service.PersonInfoService;
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

@Tag(name = "PersonInfo", description = "API for accessing person (proband's doctor, mother, father) info")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/personInfo")
public class PersonInfoController {
  private final PersonInfoService personInfoService;
  private final PersonInfoConverter personInfoConverter;

  /**
   * Retrieves all person info records.
   *
   * @return a list of all person info records
   */
  @GetMapping
  @Operation(summary = "Get all person info records", description = "Retrieves a list of all person info details for proband's doctor, mother, or father.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of person info records")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<List<PersonInfo>> getPersonInfos() {
    List<PersonInfo> personInfos = personInfoService.findAll();
    return ResponseEntity.ok(personInfos);
  }

  /**
   * Retrieves a person info record by its ID.
   *
   * @param id the ID of the person info record
   *
   * @return the person info record with the specified ID
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get a person info record by ID", description = "Retrieves the person info record with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the person info record")
  @ApiResponse(responseCode = "404", description = "Person info record not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<PersonInfo> getPersonInfoById(@PathVariable(name = "id") long id) {
    PersonInfo personInfo = personInfoService.findById(id);
    return ResponseEntity.ok(personInfo);
  }

  /**
   * Creates a new person info record.
   *
   * @param personInfoDto the DTO for the person info record to create
   *
   * @return the created person info record
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new person info record", description = "Creates a new person info record with the provided details.")
  @ApiResponse(responseCode = "201", description = "Person info record successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<PersonInfo> createPersonInfo(
      @Validated(OnCreate.class) @RequestBody PersonInfoRequestDto personInfoDto) {
    PersonInfo personInfo = personInfoConverter.toEntity(personInfoDto);
    PersonInfo createdPersonInfo = personInfoService.create(personInfo);
    return ResponseEntity.status(CREATED).body(createdPersonInfo);
  }

  /**
   * Updates an existing person info record.
   *
   * @param personInfoDto the DTO for the person info record to update
   *
   * @return the updated person info record
   */
  @PatchMapping
  @Operation(summary = "Update a person info record", description = "Updates an existing person info record with the provided details.")
  @ApiResponse(responseCode = "200", description = "Person info record successfully updated")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @ApiResponse(responseCode = "404", description = "Person info record not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<PersonInfo> updatePersonInfo(
      @Validated(OnUpdate.class) @RequestBody PersonInfoRequestDto personInfoDto) {
    PersonInfo personInfo = personInfoConverter.toEntity(personInfoDto);
    PersonInfo updatedPersonInfo = personInfoService.update(personInfo);
    return ResponseEntity.ok(updatedPersonInfo);
  }

  /**
   * Deletes a person info record by its ID.
   *
   * @param id the ID of the person info record to delete
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a person info record", description = "Deletes the person info record with the specified ID.")
  @ApiResponse(responseCode = "204", description = "Person info record successfully deleted")
  @ApiResponse(responseCode = "404", description = "Person info record not found")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deletePersonInfoById(@PathVariable(name = "id") long id) {
    personInfoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

