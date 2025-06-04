package com.vpr.app.controller;

import com.vpr.app.dto.request.PersonInfoRequestDto;
import com.vpr.app.dto.request.mappers.PersonInfoConverter;
import com.vpr.app.entity.PersonInfo;
import com.vpr.app.service.PersonInfoService;
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
class PersonInfoControllerTest {

    private static final long PERSON_INFO_ID = 1L;

    @Mock
    private PersonInfoService personInfoService;

    @Mock
    private PersonInfoConverter personInfoConverter;

    @InjectMocks
    private PersonInfoController controller;

    private PersonInfo personInfo;

    @BeforeEach
    void init() {
        personInfo = new PersonInfo();
        personInfo.setId(PERSON_INFO_ID);
        personInfo.setName("Test Person Info");
    }

    @Test
    void getPersonInfos_shouldReturnAll() {
        when(personInfoService.findAll()).thenReturn(List.of(personInfo));

        ResponseEntity<List<PersonInfo>> response = controller.getPersonInfos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(personInfo.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getPersonInfoById_shouldReturnSingle() {
        when(personInfoService.findById(PERSON_INFO_ID)).thenReturn(personInfo);

        ResponseEntity<PersonInfo> response = controller.getPersonInfoById(PERSON_INFO_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(PERSON_INFO_ID, response.getBody().getId());
    }

    @Test
    void createPersonInfo_shouldReturnCreated() {
        PersonInfoRequestDto dto = new PersonInfoRequestDto();
        when(personInfoConverter.toEntity(dto)).thenReturn(personInfo);
        when(personInfoService.create(personInfo)).thenReturn(personInfo);

        ResponseEntity<PersonInfo> response = controller.createPersonInfo(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(personInfo.getId(), response.getBody().getId());
    }

    @Test
    void updatePersonInfo_shouldReturnUpdated() {
        PersonInfoRequestDto dto = new PersonInfoRequestDto();
        when(personInfoConverter.toEntity(dto)).thenReturn(personInfo);
        when(personInfoService.update(personInfo)).thenReturn(personInfo);

        ResponseEntity<PersonInfo> response = controller.updatePersonInfo(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personInfo.getId(), response.getBody().getId());
    }

    @Test
    void deletePersonInfoById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deletePersonInfoById(PERSON_INFO_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}