package com.leverx.app.controller;

import com.leverx.app.entity.Address;
import com.leverx.app.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    @RequestMapping(value = "/{id}", method = GET)
    public Address getAddressById(@PathVariable(name = "id") long id) {
        return addressService.findById(id);
    }

    @RequestMapping(method = GET)
    public List<Address> getAddresses() {
        return addressService.findAll();
    }
}
