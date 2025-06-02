package com.vpr.app.service;

import com.vpr.app.entity.Proband;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.ProbandRepository;
import com.vpr.app.service.impl.ProbandServiceImpl;
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
class ProbandServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private ProbandRepository probandRepository;

    @InjectMocks
    private ProbandServiceImpl service;

    private Proband proband;

    @BeforeEach
    void setUp() {
        proband = new Proband();
        proband.setId(ID);
    }

    @Test
    void findById_shouldReturnEntity_whenFound() {
        // given
        when(probandRepository.findById(ID)).thenReturn(Optional.of(proband));

        // when
        Proband result = service.findById(ID);

        // then
        assertThat(result).isSameAs(proband);
        verify(probandRepository).findById(ID);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        // given
        when(probandRepository.findById(ID)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        // given
        List<Proband> list = List.of(new Proband(), new Proband());
        when(probandRepository.findAll()).thenReturn(list);

        // when
        List<Proband> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        verify(probandRepository).findAll();
    }

    @Test
    void create_shouldSaveEntity() {
        // given
        when(probandRepository.save(proband)).thenReturn(proband);

        // when
        Proband result = service.create(proband);

        // then
        assertThat(result).isSameAs(proband);
        verify(probandRepository).save(proband);
    }

    @Test
    void update_shouldSaveEntity() {
        // given
        when(probandRepository.save(proband)).thenReturn(proband);

        // when
        Proband result = service.update(proband);

        // then
        assertThat(result).isSameAs(proband);
        verify(probandRepository).save(proband);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        // given
        when(probandRepository.existsById(ID)).thenReturn(true);

        // when
        service.delete(ID);

        // then
        verify(probandRepository).existsById(ID);
        verify(probandRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        // given
        when(probandRepository.existsById(ID)).thenReturn(false);

        // then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }
}
