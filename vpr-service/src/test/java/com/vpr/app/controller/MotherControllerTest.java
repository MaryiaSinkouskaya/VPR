package com.vpr.app.controller;

import com.vpr.app.dto.request.MotherRequestDto;
import com.vpr.app.dto.request.mappers.MotherConverter;
import com.vpr.app.entity.Mother;
import com.vpr.app.service.MotherService;
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
class MotherControllerTest {

    private static final long MOTHER_ID = 1L;

    @Mock
    private MotherService motherService;

    @Mock
    private MotherConverter motherConverter;

    @InjectMocks
    private MotherController controller;

    private Mother mother;

    @BeforeEach
    void init() {
        mother = new Mother();
        mother.setId(MOTHER_ID);
        mother.setGirlSurname("Test Mother");
    }

    @Test
    void getMothers_shouldReturnAll() {
        when(motherService.findAll()).thenReturn(List.of(mother));

        ResponseEntity<List<Mother>> response = controller.getMothers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(mother.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getMotherById_shouldReturnSingle() {
        when(motherService.findById(MOTHER_ID)).thenReturn(mother);

        ResponseEntity<Mother> response = controller.getMotherById(MOTHER_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MOTHER_ID, response.getBody().getId());
    }

    @Test
    void createMother_shouldReturnCreated() {
        MotherRequestDto dto = new MotherRequestDto();
        when(motherConverter.toEntity(dto)).thenReturn(mother);
        when(motherService.create(mother)).thenReturn(mother);

        ResponseEntity<Mother> response = controller.createMother(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mother.getId(), response.getBody().getId());
    }

    @Test
    void updateMother_shouldReturnUpdated() {
        MotherRequestDto dto = new MotherRequestDto();
        when(motherConverter.toEntity(dto)).thenReturn(mother);
        when(motherService.update(mother)).thenReturn(mother);

        ResponseEntity<Mother> response = controller.updateMother(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mother.getId(), response.getBody().getId());
    }

    @Test
    void deleteMotherById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteMotherById(MOTHER_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}