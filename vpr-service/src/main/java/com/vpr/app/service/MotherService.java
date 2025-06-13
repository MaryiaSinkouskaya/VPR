package com.vpr.app.service;

import com.vpr.app.entity.Mother;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing mother entities in the system.
 * Provides CRUD operations for mother data.
 */
@Service
public interface MotherService {

    /**
     * Retrieves a mother entity by its ID.
     *
     * @param id the ID of the mother to find
     * @return the found mother entity
     */
    Mother findById(long id);

    /**
     * Retrieves all mother entities from the system.
     *
     * @return a list of all mother entities
     */
    List<Mother> findAll();

    /**
     * Creates a new mother entity in the system.
     *
     * @param mother the mother entity to create
     * @return the created mother entity with generated ID
     */
    Mother create(Mother mother);

    /**
     * Updates an existing mother entity in the system.
     *
     * @param mother the mother entity to update
     * @return the updated mother entity
     */
    Mother update(Mother mother);

    /**
     * Deletes a mother entity from the system by its ID.
     *
     * @param id the ID of the mother to delete
     */
    void delete(long id);
}
