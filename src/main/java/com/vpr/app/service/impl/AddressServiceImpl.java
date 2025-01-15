package com.vpr.app.service.impl;

import com.vpr.app.entity.Address;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.AddressRepository;
import com.vpr.app.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

  public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
  public static final String ENTITY_NAME = Address.class.getSimpleName();
  private final AddressRepository addressRepository;

  @Override
  public Address findById(long id) {
    return addressRepository.findById(id)//
        .orElseThrow(
            () -> new VprEntityNotFoundException(
                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
  }

  @Override
  public List<Address> findAll() {
    return addressRepository.findAll();
  }

  @Override
  public Address create(Address address) {
    return addressRepository.save(address);
  }

  @Override
  public Address update(Address address) {
    return addressRepository.save(address);
  }

  @Override
  public void delete(long id) {
    validateExistence(id);
    addressRepository.deleteById(id);
    log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
  }

  private void validateExistence(long id) {
    if (!addressRepository.existsById(id)) {
      log.warn("Attempted to delete non-existent {} entity with id {}",
          Address.class.getSimpleName(), id);
      throw new VprEntityNotFoundException(
          String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
    }
  }
}
