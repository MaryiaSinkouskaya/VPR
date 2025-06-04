package com.vpr.app.controller;

import com.vpr.app.dto.request.AbnormalityRequestDto;
import com.vpr.app.dto.request.mappers.AbnormalityConverter;
import com.vpr.app.entity.Abnormality;
import com.vpr.app.service.AbnormalityService;
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
class AbnormalityControllerTest {

    private static final long ABNORMALITY_ID = 1L;

    @Mock
    private AbnormalityService abnormalityService;

    @Mock
    private AbnormalityConverter abnormalityConverter;

    @InjectMocks
    private AbnormalityController controller;

    private Abnormality abnormality;

    @BeforeEach
    void init() {
        abnormality = new Abnormality();
        abnormality.setId(ABNORMALITY_ID);
        abnormality.setName("Test Abnormality");
    }

    @Test
    void getAbnormalities_shouldReturnAll() {
        when(abnormalityService.findAll()).thenReturn(List.of(abnormality));

        ResponseEntity<List<Abnormality>> response = controller.getAbnormalities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(abnormality.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getAbnormalityById_shouldReturnSingle() {
        when(abnormalityService.findById(ABNORMALITY_ID)).thenReturn(abnormality);

        ResponseEntity<Abnormality> response = controller.getAbnormalityById(ABNORMALITY_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ABNORMALITY_ID, response.getBody().getId());
    }

    @Test
    void createAbnormality_shouldReturnCreated() {
        AbnormalityRequestDto dto = new AbnormalityRequestDto();
        when(abnormalityConverter.toEntity(dto)).thenReturn(abnormality);
        when(abnormalityService.create(abnormality)).thenReturn(abnormality);

        ResponseEntity<Abnormality> response = controller.createAbnormality(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(abnormality.getId(), response.getBody().getId());
    }

    @Test
    void updateAbnormality_shouldReturnUpdated() {
        AbnormalityRequestDto dto = new AbnormalityRequestDto();
        when(abnormalityConverter.toEntity(dto)).thenReturn(abnormality);
        when(abnormalityService.update(abnormality)).thenReturn(abnormality);

        ResponseEntity<Abnormality> response = controller.updateAbnormality(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(abnormality.getId(), response.getBody().getId());
    }

    @Test
    void deleteAbnormalityById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteAbnormalityById(ABNORMALITY_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
