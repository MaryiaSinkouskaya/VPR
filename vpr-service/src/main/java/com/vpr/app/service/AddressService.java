package com.vpr.app.service;

import com.vpr.app.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing address entities in the system.
 * Provides CRUD operations for address data.
 */
@Service
public interface AddressService {
    /**
     * Retrieves an address entity by its ID.
     *
     * @param id the ID of the address to find
     * @return the found address entity
     */
    Address findById(long id);

    /**
     * Retrieves all address entities from the system.
     *
     * @return a list of all address entities
     */
    List<Address> findAll();

    /**
     * Creates a new address entity in the system.
     *
     * @param address the address entity to create
     * @return the created address entity with generated ID
     */
    Address create(Address address);

    /**
     * Updates an existing address entity in the system.
     *
     * @param address the address entity to update
     * @return the updated address entity
     */
    Address update(Address address);

    /**
     * Deletes an address entity from the system by its ID.
     *
     * @param id the ID of the address to delete
     */
    void delete(long id);
}
