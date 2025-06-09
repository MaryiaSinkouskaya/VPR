package com.vpr.app.controller;

import com.vpr.app.dto.request.AddressRequestDto;
import com.vpr.app.dto.request.mappers.AddressConverter;
import com.vpr.app.entity.Address;
import com.vpr.app.service.AddressService;
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
class AddressControllerTest {

    private static final long ADDRESS_ID = 1L;

    @Mock
    private AddressService addressService;

    @Mock
    private AddressConverter addressConverter;

    @InjectMocks
    private AddressController controller;

    private Address address;

    @BeforeEach
    void init() {
        address = new Address();
        address.setId(ADDRESS_ID);
        address.setStreet("Test Street");
    }

    @Test
    void getAddresses_shouldReturnAll() {
        when(addressService.findAll()).thenReturn(List.of(address));

        ResponseEntity<List<Address>> response = controller.getAddresses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(address.getId(), response.getBody().get(0).getId());
    }

    @Test
    void getAddressById_shouldReturnSingle() {
        when(addressService.findById(ADDRESS_ID)).thenReturn(address);

        ResponseEntity<Address> response = controller.getAddressById(ADDRESS_ID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ADDRESS_ID, response.getBody().getId());
    }

    @Test
    void createAddress_shouldReturnCreated() {
        AddressRequestDto dto = new AddressRequestDto();
        when(addressConverter.toEntity(dto)).thenReturn(address);
        when(addressService.create(address)).thenReturn(address);

        ResponseEntity<Address> response = controller.createAddress(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(address.getId(), response.getBody().getId());
    }

    @Test
    void updateAddress_shouldReturnUpdated() {
        AddressRequestDto dto = new AddressRequestDto();
        when(addressConverter.toEntity(dto)).thenReturn(address);
        when(addressService.update(address)).thenReturn(address);

        ResponseEntity<Address> response = controller.updateAddress(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(address.getId(), response.getBody().getId());
    }

    @Test
    void deleteAddressById_shouldReturnNoContent() {
        ResponseEntity<Void> response = controller.deleteAddressById(ADDRESS_ID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}