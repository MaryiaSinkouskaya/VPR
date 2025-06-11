package com.vpr.app.controller;

import com.vpr.app.dto.request.DoctorRequestDto;
import com.vpr.app.dto.request.mappers.DoctorConverter;
import com.vpr.app.entity.Doctor;
import com.vpr.app.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {

    private static final long DOCTOR_ID = 1L;

    @Mock
    private DoctorService doctorService;

    @Mock
    private DoctorConverter doctorConverter;

    @InjectMocks
    private DoctorController controller;

    private Doctor doctor;

    @BeforeEach
    void init() {
        doctor = new Doctor();
        doctor.setId(DOCTOR_ID);
        doctor.setSpeciality("Test Doctor");
    }

    @Test
    void getDoctors_shouldReturnAll() {
        when(doctorService.findAll()).thenReturn(List.of(doctor));

        ResponseEntity<List<Doctor>> response = controller.getDoctors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(doctor.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getDoctorById_shouldReturnSingle() {
        when(doctorService.findById(DOCTOR_ID)).thenReturn(doctor);

        ResponseEntity<Doctor> response = controller.getDoctorById(DOCTOR_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(DOCTOR_ID, response.getBody().getId());
    }

    @Test
    void createDoctor_shouldReturnCreated() {
        DoctorRequestDto dto = new DoctorRequestDto();
        when(doctorConverter.toEntity(dto)).thenReturn(doctor);
        when(doctorService.create(doctor)).thenReturn(doctor);

        ResponseEntity<Doctor> response = controller.createDoctor(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(doctor.getId(), response.getBody().getId());
    }

    @Test
    void updateDoctor_shouldReturnUpdated() {
        DoctorRequestDto dto = new DoctorRequestDto();
        when(doctorConverter.toEntity(dto)).thenReturn(doctor);
        when(doctorService.update(doctor)).thenReturn(doctor);

        ResponseEntity<Doctor> response = controller.updateDoctor(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctor.getId(), response.getBody().getId());
    }

    @Test
    void deleteDoctorById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteDoctorById(DOCTOR_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}