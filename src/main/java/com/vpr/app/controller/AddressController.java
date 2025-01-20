package com.vpr.app.controller;

import com.vpr.app.dto.request.AddressRequestDto;
import com.vpr.app.entity.Address;
import com.vpr.app.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address", description = "API for accessing the address data")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping(value = "/{id}")
    public Address getAddressById(@PathVariable(name = "id") long id) {
        return addressService.findById(id);
    }

    @GetMapping()
    public List<Address> getAddresses() {
        return addressService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Address createAddress(@Valid @RequestBody AddressRequestDto addressDto) {
        Address address = Address.builder()
                .town(addressDto.getTown())
                .street(addressDto.getStreet())
                .building(addressDto.getBuilding())
                .apartment(addressDto.getApartment())
                .build();
        return addressService.create(address);
    }

    @PatchMapping()
    public Address updateAddress(@Valid @RequestBody AddressRequestDto addressDto) {
        Address address = Address.builder()
                .town(addressDto.getTown())
                .street(addressDto.getStreet())
                .building(addressDto.getBuilding())
                .apartment(addressDto.getApartment())
                .build();
        return addressService.update(address);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddressById(@PathVariable(name = "id") long id) {
        addressService.delete(id);
    }

}
