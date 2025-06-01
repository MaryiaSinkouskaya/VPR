package com.vpr.app.service;

import com.vpr.app.entity.Address;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.AddressRepository;
import com.vpr.app.service.impl.AddressServiceImpl;
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
public class AddressServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl service;

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
        address.setId(ID);
    }

    @Test
    void findById_shouldReturnEntity_whenFound() {
        // given
        when(addressRepository.findById(ID)).thenReturn(Optional.of(address));

        // when
        Address result = service.findById(ID);

        // then
        assertThat(result).isSameAs(address);
        verify(addressRepository).findById(ID);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        // given
        when(addressRepository.findById(ID)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }

    @Test
    void findAll_shouldReturnAllEntities() {
        // given
        List<Address> list = List.of(new Address(), new Address());
        when(addressRepository.findAll()).thenReturn(list);

        // when
        List<Address> result = service.findAll();

        // then
        assertThat(result).hasSize(2);
        verify(addressRepository).findAll();
    }

    @Test
    void create_shouldSaveEntity() {
        // given
        when(addressRepository.save(address)).thenReturn(address);

        // when
        Address result = service.create(address);

        // then
        assertThat(result).isSameAs(address);
        verify(addressRepository).save(address);
    }

    @Test
    void update_shouldSaveEntity() {
        // given
        when(addressRepository.save(address)).thenReturn(address);

        // when
        Address result = service.update(address);

        // then
        assertThat(result).isSameAs(address);
        verify(addressRepository).save(address);
    }

    @Test
    void delete_shouldDelete_whenEntityExists() {
        // given
        when(addressRepository.existsById(ID)).thenReturn(true);

        // when
        service.delete(ID);

        // then
        verify(addressRepository).existsById(ID);
        verify(addressRepository).deleteById(ID);
    }

    @Test
    void delete_shouldThrow_whenEntityDoesNotExist() {
        // given
        when(addressRepository.existsById(ID)).thenReturn(false);

        // then
        assertThatThrownBy(() -> service.delete(ID))
                .isInstanceOf(VprEntityNotFoundException.class)
                .hasMessageContaining("does not exist");
    }
}

