package com.vpr.app.controller;

import com.vpr.app.dto.request.AddressRequestDto;
import com.vpr.app.dto.request.mappers.AddressConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Address;
import com.vpr.app.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address", description = "API for accessing the address data")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;
    private final AddressConverter addressConverter;

    @GetMapping(value = "/{id}")
    public Address getAddressById(@PathVariable(name = "id") long id) {
        return addressService.findById(id);
    }

    @GetMapping()
    public List<Address> getAddresses() {
        return addressService.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Address createAddress(@Validated(OnCreate.class) @RequestBody AddressRequestDto addressDto) {
        Address address = addressConverter.toEntity(addressDto);
        return addressService.create(address);
    }

    @PatchMapping()
    public Address updateAddress(@Validated(OnUpdate.class) @RequestBody AddressRequestDto addressDto) {
        Address address = addressConverter.toEntity(addressDto);
        return addressService.update(address);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAddressById(@PathVariable(name = "id") long id) {
        addressService.delete(id);
    }

}
