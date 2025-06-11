package com.vpr.app.service;

import com.vpr.app.entity.PersonInfo;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.PersonInfoRepository;
import com.vpr.app.service.impl.PersonInfoServiceImpl;
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
class PersonInfoServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private PersonInfoRepository personInfoRepository;

    @InjectMocks
    private PersonInfoServiceImpl service;

    private PersonInfo personInfo;

    @BeforeEach
    void setUp() {
        personInfo = new PersonInfo();
        personInfo.setId(ID);
    }

    @Test
    void findById_shouldReturnEntity_whenFound() {
        // given
        when(personInfoRepository.findById(ID)).thenReturn(Optional.of(personInfo));

        // when
        PersonInfo result = service.findById(ID);

        // then
        assertThat(result).isSameAs(personInfo);
        verify(personInfoRepository).findById(ID);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        // given
        when(personInfoRepository.findById(ID)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        // given
        List<PersonInfo> list = List.of(new PersonInfo(), new PersonInfo());
        when(personInfoRepository.findAll()).thenReturn(list);

        // when
        List<PersonInfo> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        verify(personInfoRepository).findAll();
    }

    @Test
    void create_shouldSaveEntity() {
        // given
        when(personInfoRepository.save(personInfo)).thenReturn(personInfo);

        // when
        PersonInfo result = service.create(personInfo);

        // then
        assertThat(result).isSameAs(personInfo);
        verify(personInfoRepository).save(personInfo);
    }

    @Test
    void update_shouldSaveEntity() {
        // given
        when(personInfoRepository.save(personInfo)).thenReturn(personInfo);

        // when
        PersonInfo result = service.update(personInfo);

        // then
        assertThat(result).isSameAs(personInfo);
        verify(personInfoRepository).save(personInfo);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        // given
        when(personInfoRepository.existsById(ID)).thenReturn(true);

        // when
        service.delete(ID);

        // then
        verify(personInfoRepository).existsById(ID);
        verify(personInfoRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        // given
        when(personInfoRepository.existsById(ID)).thenReturn(false);

        // then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }
}
