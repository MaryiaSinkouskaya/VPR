package com.vpr.app.controller;

import com.vpr.app.dto.request.ProbandRequestDto;
import com.vpr.app.dto.request.mappers.ProbandConverter;
import com.vpr.app.entity.Proband;
import com.vpr.app.service.ProbandService;
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
class ProbandControllerTest {

    private static final long PROBAND_ID = 1L;

    @Mock
    private ProbandService probandService;

    @Mock
    private ProbandConverter probandConverter;

    @InjectMocks
    private ProbandController controller;

    private Proband proband;

    @BeforeEach
    void init() {
        proband = new Proband();
        proband.setId(PROBAND_ID);
    }

    @Test
    void getProbands_shouldReturnAll() {
        when(probandService.findAll()).thenReturn(List.of(proband));

        ResponseEntity<List<Proband>> response = controller.getProbands();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(proband.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getProbandById_shouldReturnSingle() {
        when(probandService.findById(PROBAND_ID)).thenReturn(proband);

        ResponseEntity<Proband> response = controller.getProbandById(PROBAND_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(PROBAND_ID, response.getBody().getId());
    }

    @Test
    void createProband_shouldReturnCreated() {
        ProbandRequestDto dto = new ProbandRequestDto();
        when(probandConverter.toEntity(dto)).thenReturn(proband);
        when(probandService.create(proband)).thenReturn(proband);

        ResponseEntity<Proband> response = controller.createProband(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(proband.getId(), response.getBody().getId());
    }

    @Test
    void updateProband_shouldReturnUpdated() {
        ProbandRequestDto dto = new ProbandRequestDto();
        when(probandConverter.toEntity(dto)).thenReturn(proband);
        when(probandService.update(proband)).thenReturn(proband);

        ResponseEntity<Proband> response = controller.updateProband(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(proband.getId(), response.getBody().getId());
    }

    @Test
    void deleteProbandById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteProbandById(PROBAND_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}