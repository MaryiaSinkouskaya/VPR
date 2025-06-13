package com.vpr.app.service;

import com.vpr.app.entity.Abnormality;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing abnormality entities in the system.
 * Provides CRUD operations for abnormality data.
 */
@Service
public interface AbnormalityService {
    /**
     * Retrieves an abnormality entity by its ID.
     *
     * @param id the ID of the abnormality to find
     * @return the found abnormality entity
     */
    Abnormality findById(long id);

    /**
     * Retrieves all abnormality entities from the system.
     *
     * @return a list of all abnormality entities
     */
    List<Abnormality> findAll();

    /**
     * Creates a new abnormality entity in the system.
     *
     * @param abnormality the abnormality entity to create
     * @return the created abnormality entity with generated ID
     */
    Abnormality create(Abnormality abnormality);

    /**
     * Updates an existing abnormality entity in the system.
     *
     * @param abnormality the abnormality entity to update
     * @return the updated abnormality entity
     */
    Abnormality update(Abnormality abnormality);

    /**
     * Deletes an abnormality entity from the system by its ID.
     *
     * @param id the ID of the abnormality to delete
     */
    void delete(long id);
}
