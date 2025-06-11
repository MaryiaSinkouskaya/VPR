package com.vpr.app.service;

import com.vpr.app.entity.Abnormality;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.AbnormalityRepository;
import com.vpr.app.service.impl.AbnormalityServiceImpl;
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
class AbnormalityServiceImplTest {

    private static final long ID = 10L;

    @Mock
    private AbnormalityRepository abnormalityRepository;

    @InjectMocks
    private AbnormalityServiceImpl service;

    @Test
    void findById_shouldReturnEntity_whenFound() {
        //given
        Abnormality abnormality = new Abnormality();
        abnormality.setId(ID);
        when(abnormalityRepository.findById(ID)).thenReturn(Optional.of(abnormality));

        //when
        Abnormality result = service.findById(ID);

        //then
        assertThat(result).isSameAs(abnormality);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        //given
        when(abnormalityRepository.findById(ID)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        //given
        List<Abnormality> list = List.of(new Abnormality(), new Abnormality());
        when(abnormalityRepository.findAll()).thenReturn(list);

        //when
        List<Abnormality> result = service.findAll();

        //then
        assertThat(result).hasSize(2);
    }

    @Test
    void create_shouldSaveEntity() {
        //given
        Abnormality abnormality = new Abnormality();
        when(abnormalityRepository.save(abnormality)).thenReturn(abnormality);

        //when
        Abnormality result = service.create(abnormality);

        //then
        assertThat(result).isSameAs(abnormality);
        verify(abnormalityRepository).save(abnormality);
    }

    @Test
    void update_shouldSaveEntity() {
        //given
        Abnormality abnormality = new Abnormality();
        when(abnormalityRepository.save(abnormality)).thenReturn(abnormality);

        //when
        Abnormality result = service.update(abnormality);

        //then
        assertThat(result).isSameAs(abnormality);
        verify(abnormalityRepository).save(abnormality);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        //given
        when(abnormalityRepository.existsById(ID)).thenReturn(true);

        //when
        service.delete(ID);

        //then
        verify(abnormalityRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        //given
        when(abnormalityRepository.existsById(ID)).thenReturn(false);

        //then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class);
    }
}

