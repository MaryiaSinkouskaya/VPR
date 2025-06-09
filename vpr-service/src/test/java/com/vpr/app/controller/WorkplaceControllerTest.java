package com.vpr.app.controller;

import com.vpr.app.dto.request.WorkplaceRequestDto;
import com.vpr.app.dto.request.mappers.WorkplaceConverter;
import com.vpr.app.entity.Workplace;
import com.vpr.app.service.WorkplaceService;
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
class WorkplaceControllerTest {

    private static final long WORKPLACE_ID = 1L;

    @Mock
    private WorkplaceService workplaceService;

    @Mock
    private WorkplaceConverter workplaceConverter;

    @InjectMocks
    private WorkplaceController controller;

    private Workplace workplace;

    @BeforeEach
    void init() {
        workplace = new Workplace();
        workplace.setId(WORKPLACE_ID);
        workplace.setJobType("Test Workplace");
    }

    @Test
    void getWorks_shouldReturnAll() {
        when(workplaceService.findAll()).thenReturn(List.of(workplace));

        ResponseEntity<List<Workplace>> response = controller.getWorks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(workplace.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getWorkById_shouldReturnSingle() {
        when(workplaceService.findById(WORKPLACE_ID)).thenReturn(workplace);

        ResponseEntity<Workplace> response = controller.getWorkById(WORKPLACE_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(WORKPLACE_ID, response.getBody().getId());
    }

    @Test
    void createWork_shouldReturnCreated() {
        WorkplaceRequestDto dto = new WorkplaceRequestDto();
        when(workplaceConverter.toEntity(dto)).thenReturn(workplace);
        when(workplaceService.create(workplace)).thenReturn(workplace);

        ResponseEntity<Workplace> response = controller.createWork(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(workplace.getId(), response.getBody().getId());
    }

    @Test
    void updateWork_shouldReturnUpdated() {
        WorkplaceRequestDto dto = new WorkplaceRequestDto();
        when(workplaceConverter.toEntity(dto)).thenReturn(workplace);
        when(workplaceService.update(workplace)).thenReturn(workplace);

        ResponseEntity<Workplace> response = controller.updateWork(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(workplace.getId(), response.getBody().getId());
    }

    @Test
    void deleteWorkById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteWorkById(WORKPLACE_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}