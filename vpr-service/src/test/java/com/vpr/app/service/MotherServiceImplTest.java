package com.vpr.app.service;

import com.vpr.app.entity.Mother;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.MotherRepository;
import com.vpr.app.service.impl.MotherServiceImpl;
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
class MotherServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private MotherRepository motherRepository;

    @InjectMocks
    private MotherServiceImpl service;

    private Mother mother;

    @BeforeEach
    void setUp() {
        mother = new Mother();
        mother.setId(ID);
    }

    @Test
    void findById_shouldReturnEntity_whenFound() {
        // given
        when(motherRepository.findById(ID)).thenReturn(Optional.of(mother));

        // when
        Mother result = service.findById(ID);

        // then
        assertThat(result).isSameAs(mother);
        verify(motherRepository).findById(ID);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        // given
        when(motherRepository.findById(ID)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        // given
        List<Mother> list = List.of(new Mother(), new Mother());
        when(motherRepository.findAll()).thenReturn(list);

        // when
        List<Mother> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        verify(motherRepository).findAll();
    }

    @Test
    void create_shouldSaveEntity() {
        // given
        when(motherRepository.save(mother)).thenReturn(mother);

        // when
        Mother result = service.create(mother);

        // then
        assertThat(result).isSameAs(mother);
        verify(motherRepository).save(mother);
    }

    @Test
    void update_shouldSaveEntity() {
        // given
        when(motherRepository.save(mother)).thenReturn(mother);

        // when
        Mother result = service.update(mother);

        // then
        assertThat(result).isSameAs(mother);
        verify(motherRepository).save(mother);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        // given
        when(motherRepository.existsById(ID)).thenReturn(true);

        // when
        service.delete(ID);

        // then
        verify(motherRepository).existsById(ID);
        verify(motherRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        // given
        when(motherRepository.existsById(ID)).thenReturn(false);

        // then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }
}
