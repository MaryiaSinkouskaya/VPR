package com.vpr.app.controller;

import com.vpr.app.entity.Doctor;
import com.vpr.app.service.DoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "Doctor", description = "API for accessing the doc data")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
  private final DoctorService doctorService;

  @GetMapping()
  public List<Doctor> getDoctors() {
    return doctorService.findAll();
  }

  @GetMapping(value = "/{id}")
  public Doctor getDoctorById(@PathVariable(name = "id") long id) {
    return doctorService.findById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Doctor createDoctor(@RequestBody Doctor doctor) {
    return doctorService.create(doctor);
  }

  @PutMapping()
  public Doctor updateDoctor(@RequestBody Doctor doctor) {
    return doctorService.update(doctor);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteDoctorById(@PathVariable(name = "id") long id) {
    doctorService.delete(id);
  }
}
