package com.vpr.app.controller;

import com.vpr.app.dto.request.ProbandDRequestDto;
import com.vpr.app.dto.request.mappers.ProbandDConverter;
import com.vpr.app.entity.ProbandD;
import com.vpr.app.service.ProbandDService;
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
class ProbandDControllerTest {

    private static final long PROBANDD_ID = 1L;

    @Mock
    private ProbandDService probandDService;

    @Mock
    private ProbandDConverter probandDConverter;

    @InjectMocks
    private ProbandDController controller;

    private ProbandD probandD;

    @BeforeEach
    void init() {
        probandD = new ProbandD();
        probandD.setId(PROBANDD_ID);
    }

    @Test
    void getProbandDs_shouldReturnAll() {
        when(probandDService.findAll()).thenReturn(List.of(probandD));

        ResponseEntity<List<ProbandD>> response = controller.getProbandDs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(probandD.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getProbandDById_shouldReturnSingle() {
        when(probandDService.findById(PROBANDD_ID)).thenReturn(probandD);

        ResponseEntity<ProbandD> response = controller.getProbandDById(PROBANDD_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(PROBANDD_ID, response.getBody().getId());
    }

    @Test
    void createProbandD_shouldReturnCreated() {
        ProbandDRequestDto dto = new ProbandDRequestDto();
        when(probandDConverter.toEntity(dto)).thenReturn(probandD);
        when(probandDService.create(probandD)).thenReturn(probandD);

        ResponseEntity<ProbandD> response = controller.createProbandD(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(probandD.getId(), response.getBody().getId());
    }

    @Test
    void updateProbandD_shouldReturnUpdated() {
        ProbandDRequestDto dto = new ProbandDRequestDto();
        when(probandDConverter.toEntity(dto)).thenReturn(probandD);
        when(probandDService.update(probandD)).thenReturn(probandD);

        ResponseEntity<ProbandD> response = controller.updateProbandD(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(probandD.getId(), response.getBody().getId());
    }

    @Test
    void deleteProbandDById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteProbandDById(PROBANDD_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}