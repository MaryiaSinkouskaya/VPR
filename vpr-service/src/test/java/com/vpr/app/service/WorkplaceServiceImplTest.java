package com.vpr.app.service;

import com.vpr.app.entity.Workplace;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.WorkplaceRepository;
import com.vpr.app.service.impl.WorkplaceServiceImpl;
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
class WorkplaceServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private WorkplaceRepository workplaceRepository;

    @InjectMocks
    private WorkplaceServiceImpl service;

    private Workplace workplace;

    @BeforeEach
    void setUp() {
        workplace = new Workplace();
        workplace.setId(ID);
    }

    @Test
    void findById_shouldReturnEntity_whenFound() {
        // before
        when(workplaceRepository.findById(ID)).thenReturn(Optional.of(workplace));

        // when
        Workplace result = service.findById(ID);

        // then
        assertThat(result).isSameAs(workplace);
        verify(workplaceRepository).findById(ID);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        // before
        when(workplaceRepository.findById(ID)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        // before
        List<Workplace> list = List.of(new Workplace(), new Workplace());
        when(workplaceRepository.findAll()).thenReturn(list);

        // when
        List<Workplace> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        verify(workplaceRepository).findAll();
    }

    @Test
    void create_shouldSaveEntity() {
        // before
        when(workplaceRepository.save(workplace)).thenReturn(workplace);

        // when
        Workplace result = service.create(workplace);

        // then
        assertThat(result).isSameAs(workplace);
        verify(workplaceRepository).save(workplace);
    }

    @Test
    void update_shouldSaveEntity() {
        // before
        when(workplaceRepository.save(workplace)).thenReturn(workplace);

        // when
        Workplace result = service.update(workplace);

        // then
        assertThat(result).isSameAs(workplace);
        verify(workplaceRepository).save(workplace);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        // before
        when(workplaceRepository.existsById(ID)).thenReturn(true);

        // when
        service.delete(ID);

        // then
        verify(workplaceRepository).existsById(ID);
        verify(workplaceRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        // before
        when(workplaceRepository.existsById(ID)).thenReturn(false);

        // then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }
}
