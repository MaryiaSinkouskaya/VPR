package com.vpr.app.service;

import com.vpr.app.entity.Doctor;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.DoctorRepository;
import com.vpr.app.service.impl.DoctorServiceImpl;
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
class DoctorServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl service;

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(ID);
    }

    @Test
    void findById_shouldReturnEntity_whenFound() {
        // given
        when(doctorRepository.findById(ID)).thenReturn(Optional.of(doctor));

        // when
        Doctor result = service.findById(ID);

        // then
        assertThat(result).isSameAs(doctor);
        verify(doctorRepository).findById(ID);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        // given
        when(doctorRepository.findById(ID)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        // given
        List<Doctor> list = List.of(new Doctor(), new Doctor());
        when(doctorRepository.findAll()).thenReturn(list);

        // when
        List<Doctor> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        verify(doctorRepository).findAll();
    }

    @Test
    void create_shouldSaveEntity() {
        // given
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        // when
        Doctor result = service.create(doctor);

        // then
        assertThat(result).isSameAs(doctor);
        verify(doctorRepository).save(doctor);
    }

    @Test
    void update_shouldSaveEntity() {
        // given
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        // when
        Doctor result = service.update(doctor);

        // then
        assertThat(result).isSameAs(doctor);
        verify(doctorRepository).save(doctor);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        // given
        when(doctorRepository.existsById(ID)).thenReturn(true);

        // when
        service.delete(ID);

        // then
        verify(doctorRepository).existsById(ID);
        verify(doctorRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        // given
        when(doctorRepository.existsById(ID)).thenReturn(false);

        // then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }
}
