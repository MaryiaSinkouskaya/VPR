package com.vpr.app.service;

import com.vpr.app.entity.Workplace;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing workplace entities in the system.
 * Provides CRUD operations for workplace data.
 */
@Service
public interface WorkplaceService {
    /**
     * Retrieves a workplace entity by its ID.
     *
     * @param id the ID of the workplace to find
     * @return the found workplace entity
     */
    Workplace findById(long id);

    /**
     * Retrieves all workplace entities from the system.
     *
     * @return a list of all workplace entities
     */
    List<Workplace> findAll();

    /**
     * Creates a new workplace entity in the system.
     *
     * @param workplace the workplace entity to create
     * @return the created workplace entity with generated ID
     */
    Workplace create(Workplace workplace);

    /**
     * Updates an existing workplace entity in the system.
     *
     * @param workplace the workplace entity to update
     * @return the updated workplace entity
     */
    Workplace update(Workplace workplace);

    /**
     * Deletes a workplace entity from the system by its ID.
     *
     * @param id the ID of the workplace to delete
     */
    void delete(long id);
}
