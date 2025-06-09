package com.vpr.app.controller;

import com.vpr.app.dto.request.OrganizationRequestDto;
import com.vpr.app.dto.request.mappers.OrganizationConverter;
import com.vpr.app.entity.Organization;
import com.vpr.app.service.OrganizationService;
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
class OrganizationControllerTest {

    private static final long ORGANIZATION_ID = 1L;

    @Mock
    private OrganizationService organizationService;

    @Mock
    private OrganizationConverter organizationConverter;

    @InjectMocks
    private OrganizationController controller;

    private Organization organization;

    @BeforeEach
    void init() {
        organization = new Organization();
        organization.setId(ORGANIZATION_ID);
        organization.setName("Test Organization");
    }

    @Test
    void getOrganizations_shouldReturnAll() {
        when(organizationService.findAll()).thenReturn(List.of(organization));

        ResponseEntity<List<Organization>> response = controller.getOrganizations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(organization.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getOrganizationById_shouldReturnSingle() {
        when(organizationService.findById(ORGANIZATION_ID)).thenReturn(organization);

        ResponseEntity<Organization> response = controller.getOrganizationById(ORGANIZATION_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ORGANIZATION_ID, response.getBody().getId());
    }

    @Test
    void createOrganization_shouldReturnCreated() {
        OrganizationRequestDto dto = new OrganizationRequestDto();
        when(organizationConverter.toEntity(dto)).thenReturn(organization);
        when(organizationService.create(organization)).thenReturn(organization);

        ResponseEntity<Organization> response = controller.createOrganization(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(organization.getId(), response.getBody().getId());
    }

    @Test
    void updateOrganization_shouldReturnUpdated() {
        OrganizationRequestDto dto = new OrganizationRequestDto();
        when(organizationConverter.toEntity(dto)).thenReturn(organization);
        when(organizationService.update(organization)).thenReturn(organization);

        ResponseEntity<Organization> response = controller.updateOrganization(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organization.getId(), response.getBody().getId());
    }

    @Test
    void deleteOrganizationById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteOrganizationById(ORGANIZATION_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}