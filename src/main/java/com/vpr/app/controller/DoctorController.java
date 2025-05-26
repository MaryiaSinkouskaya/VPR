package com.vpr.app.controller;

import static org.springframework.http.HttpStatus.CREATED;
import com.vpr.app.dto.request.DoctorRequestDto;
import com.vpr.app.dto.request.mappers.DoctorConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Doctor;
import com.vpr.app.service.DoctorService;
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

@Tag(name = "Doctor", description = "API for accessing and managing doctor data")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
  private final DoctorService doctorService;
  private final DoctorConverter doctorConverter;

  /**
   * Retrieves all doctors.
   *
   * @return a list of all doctors
   */
  @GetMapping
  @Operation(summary = "Get all doctors", description = "Retrieves a list of all doctors.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of doctors")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<List<Doctor>> getDoctors() {
    List<Doctor> doctors = doctorService.findAll();
    return ResponseEntity.ok(doctors);
  }

  /**
   * Retrieves a doctor by its ID.
   *
   * @param id the ID of the doctor
   *
   * @return the doctor with the specified ID
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get a doctor by ID", description = "Retrieves the doctor with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the doctor")
  @ApiResponse(responseCode = "404", description = "Doctor not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public ResponseEntity<Doctor> getDoctorById(@PathVariable(name = "id") long id) {
    Doctor doctor = doctorService.findById(id);
    return ResponseEntity.ok(doctor);
  }

  /**
   * Creates a new doctor.
   *
   * @param doctorDto the DTO for the doctor to create
   *
   * @return the created doctor
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new doctor", description = "Creates a new doctor with the provided details.")
  @ApiResponse(responseCode = "201", description = "Doctor successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Doctor> createDoctor(
      @Validated(OnCreate.class) @RequestBody DoctorRequestDto doctorDto) {
    Doctor doctor = doctorConverter.toEntity(doctorDto);
    Doctor createdDoctor = doctorService.create(doctor);
    return ResponseEntity.status(CREATED).body(createdDoctor);
  }

  /**
   * Updates an existing doctor.
   *
   * @param doctorDto the DTO for the doctor to update
   *
   * @return the updated doctor
   */
  @PatchMapping
  @Operation(summary = "Update a doctor", description = "Updates an existing doctor with the provided details.")
  @ApiResponse(responseCode = "200", description = "Doctor successfully updated")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @ApiResponse(responseCode = "404", description = "Doctor not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public ResponseEntity<Doctor> updateDoctor(
      @Validated(OnUpdate.class) @RequestBody DoctorRequestDto doctorDto) {
    Doctor doctor = doctorConverter.toEntity(doctorDto);
    Doctor updatedDoctor = doctorService.update(doctor);
    return ResponseEntity.ok(updatedDoctor);
  }

  /**
   * Deletes a doctor by its ID.
   *
   * @param id the ID of the doctor to delete
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a doctor", description = "Deletes the doctor with the specified ID.")
  @ApiResponse(responseCode = "204", description = "Doctor successfully deleted")
  @ApiResponse(responseCode = "404", description = "Doctor not found")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> deleteDoctorById(@PathVariable(name = "id") long id) {
    doctorService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
