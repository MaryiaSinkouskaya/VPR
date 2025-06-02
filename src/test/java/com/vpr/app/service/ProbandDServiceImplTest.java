package com.vpr.app.service;

import com.vpr.app.entity.ProbandD;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.ProbandDRepository;
import com.vpr.app.service.impl.ProbandDServiceImpl;
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
class ProbandDServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private ProbandDRepository probandDRepository;

    @InjectMocks
    private ProbandDServiceImpl service;

    private ProbandD probandD;

    @BeforeEach
    void setUp() {
        probandD = new ProbandD();
        probandD.setId(ID);
    }

    @Test
    void findById_shouldReturnEntity_whenFound() {
        // given
        when(probandDRepository.findById(ID)).thenReturn(Optional.of(probandD));

        // when
        ProbandD result = service.findById(ID);

        // then
        assertThat(result).isSameAs(probandD);
        verify(probandDRepository).findById(ID);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        // given
        when(probandDRepository.findById(ID)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        // given
        List<ProbandD> list = List.of(new ProbandD(), new ProbandD());
        when(probandDRepository.findAll()).thenReturn(list);

        // when
        List<ProbandD> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        verify(probandDRepository).findAll();
    }

    @Test
    void create_shouldSaveEntity() {
        // given
        when(probandDRepository.save(probandD)).thenReturn(probandD);

        // when
        ProbandD result = service.create(probandD);

        // then
        assertThat(result).isSameAs(probandD);
        verify(probandDRepository).save(probandD);
    }

    @Test
    void update_shouldSaveEntity() {
        // given
        when(probandDRepository.save(probandD)).thenReturn(probandD);

        // when
        ProbandD result = service.update(probandD);

        // then
        assertThat(result).isSameAs(probandD);
        verify(probandDRepository).save(probandD);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        // given
        when(probandDRepository.existsById(ID)).thenReturn(true);

        // when
        service.delete(ID);

        // then
        verify(probandDRepository).existsById(ID);
        verify(probandDRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        // given
        when(probandDRepository.existsById(ID)).thenReturn(false);

        // then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }
}
