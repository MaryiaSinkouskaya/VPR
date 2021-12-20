package com.vpr.app.controller;

import com.vpr.app.entity.Address;
import com.vpr.app.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

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

    @RequestMapping(method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Address createAddress(@RequestBody Address address) {
        return addressService.create(address);
    }

    @RequestMapping(method = PUT)
    public Address updateAddress(@RequestBody Address address) {
        return addressService.update(address);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public void deleteAddressById(@PathVariable(name = "id") long id) {
         addressService.delete(id);
    }

}
