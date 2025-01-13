package com.vpr.app.controller;

import com.vpr.app.entity.Address;
import com.vpr.app.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
  public Address createAddress(@RequestBody Address address) {
    return addressService.create(address);
  }

  @PutMapping()
  public Address updateAddress(@RequestBody Address address) {
    return addressService.update(address);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteAddressById(@PathVariable(name = "id") long id) {
    addressService.delete(id);
  }

}
