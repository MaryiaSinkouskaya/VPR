package com.vpr.app.service.impl;

import com.vpr.app.audit.log.annotation.AuditCreate;
import com.vpr.app.audit.log.annotation.AuditDelete;
import com.vpr.app.audit.log.annotation.AuditUpdate;
import com.vpr.app.entity.Address;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.AddressRepository;
import com.vpr.app.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link AddressService} interface that provides CRUD operations
 * for managing address entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class AddressServiceImpl implements AddressService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Address.class.getSimpleName();
    private final AddressRepository addressRepository;

    /**
     * Retrieves an address entity by its ID.
     *
     * @param id the ID of the address to find
     * @return the found address entity
     * @throws VprEntityNotFoundException if no address exists with the given ID
     */
    @Override
    public Address findById(long id) {
        return addressRepository.findById(id)//
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all address entities from the system.
     *
     * @return a list of all address entities
     */
    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    /**
     * Creates a new address entity in the system.
     *
     * @param address the address entity to create
     * @return the created address entity with generated ID
     */
    @Override
    @AuditCreate(entity = "Address")
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    /**
     * Updates an existing address entity in the system.
     *
     * @param address the address entity to update
     * @return the updated address entity
     */
    @Override
    @AuditUpdate(entity = "Address")
    public Address update(Address address) {
        return addressRepository.save(address);
    }

    /**
     * Deletes an address entity from the system by its ID.
     *
     * @param id the ID of the address to delete
     * @throws VprEntityNotFoundException if no address exists with the given ID
     */
    @Override
    @AuditDelete(entity = "Address")
    public void delete(long id) {
        validateExistence(id);
        addressRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of an address entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no address exists with the given ID
     */
    private void validateExistence(long id) {
        if (!addressRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}",
                    Address.class.getSimpleName(), id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}
