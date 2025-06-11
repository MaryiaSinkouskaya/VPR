package com.vpr.app.service;

import com.vpr.app.entity.Organization;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.OrganizationRepository;
import com.vpr.app.service.impl.OrganizationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationServiceImpl service;

    private Organization organization;

    @BeforeEach
    void setUp() {
        organization = new Organization();
        organization.setId(ID);
    }

    @Test
    void findById_shouldReturnEntity_whenFound() {
        // given
        when(organizationRepository.findById(ID)).thenReturn(Optional.of(organization));

        // when
        Organization result = service.findById(ID);

        // then
        assertThat(result).isSameAs(organization);
        verify(organizationRepository).findById(ID);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        // given
        when(organizationRepository.findById(ID)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        // given
        List<Organization> list = List.of(new Organization(), new Organization());
        when(organizationRepository.findAll()).thenReturn(list);

        // when
        List<Organization> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        verify(organizationRepository).findAll();
    }

    @Test
    void create_shouldSaveEntity() {
        // given
        when(organizationRepository.save(organization)).thenReturn(organization);

        // when
        Organization result = service.create(organization);

        // then
        assertThat(result).isSameAs(organization);
        verify(organizationRepository).save(organization);
    }

    @Test
    void update_shouldSaveEntity() {
        // given
        when(organizationRepository.save(organization)).thenReturn(organization);

        // when
        Organization result = service.update(organization);

        // then
        assertThat(result).isSameAs(organization);
        verify(organizationRepository).save(organization);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        // given
        when(organizationRepository.existsById(ID)).thenReturn(true);

        // when
        service.delete(ID);

        // then
        verify(organizationRepository).existsById(ID);
        verify(organizationRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        // given
        when(organizationRepository.existsById(ID)).thenReturn(false);

        // then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }
}
