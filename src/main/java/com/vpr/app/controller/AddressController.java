package com.vpr.app.controller;

import com.vpr.app.dto.request.AddressRequestDto;
import com.vpr.app.dto.request.mappers.AddressConverter;
import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.entity.Address;
import com.vpr.app.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  private final AddressConverter addressConverter;

  /**
   * Retrieves an address by its ID.
   *
   * @param id the ID of the address
   *
   * @return the address with the specified ID
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get an address by ID", description = "Retrieves the address with the specified ID.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the address")
  @ApiResponse(responseCode = "404", description = "Address not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public Address getAddressById(@PathVariable(name = "id") long id) {
    return addressService.findById(id);
  }

  /**
   * Retrieves all addresses.
   *
   * @return a list of all addresses
   */
  @GetMapping
  @Operation(summary = "Get all addresses", description = "Retrieves a list of all addresses.")
  @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of addresses")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
  public List<Address> getAddresses() {
    return addressService.findAll();
  }

  /**
   * Creates a new address.
   *
   * @param addressDto the DTO for the address to create
   *
   * @return the created address
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new address", description = "Creates a new address with the provided details.")
  @ApiResponse(responseCode = "201", description = "Address successfully created")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public Address createAddress(@Validated(OnCreate.class) @RequestBody AddressRequestDto addressDto) {
    Address address = addressConverter.toEntity(addressDto);
    return addressService.create(address);
  }

  /**
   * Updates an existing address.
   *
   * @param addressDto the DTO for the address to update
   *
   * @return the updated address
   */
  @PatchMapping
  @Operation(summary = "Update an address", description = "Updates an existing address with the provided details.")
  @ApiResponse(responseCode = "200", description = "Address successfully updated")
  @ApiResponse(responseCode = "400", description = "Invalid input provided")
  @ApiResponse(responseCode = "404", description = "Address not found")
  @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
  public Address updateAddress(@Validated(OnUpdate.class) @RequestBody AddressRequestDto addressDto) {
    Address address = addressConverter.toEntity(addressDto);
    return addressService.update(address);
  }

  /**
   * Deletes an address by its ID.
   *
   * @param id the ID of the address to delete
   */
  @DeleteMapping("/{id}")
  @Operation(summary = "Delete an address", description = "Deletes the address with the specified ID.")
  @ApiResponse(responseCode = "204", description = "Address successfully deleted")
  @ApiResponse(responseCode = "404", description = "Address not found")
  @PreAuthorize("hasRole('ADMIN')")
  public void deleteAddressById(@PathVariable(name = "id") long id) {
    addressService.delete(id);
  }
}
