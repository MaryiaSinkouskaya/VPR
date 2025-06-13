package com.vpr.app.service;

import com.vpr.app.entity.Proband;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing proband entities in the system.
 * Provides CRUD operations for proband data.
 */
@Service
public interface ProbandService {

    /**
     * Retrieves a proband entity by its ID.
     *
     * @param id the ID of the proband to find
     * @return the found proband entity
     */
    Proband findById(long id);

    /**
     * Retrieves all proband entities from the system.
     *
     * @return a list of all proband entities
     */
    List<Proband> findAll();

    /**
     * Creates a new proband entity in the system.
     *
     * @param proband the proband entity to create
     * @return the created proband entity with generated ID
     */
    Proband create(Proband proband);

    /**
     * Updates an existing proband entity in the system.
     *
     * @param proband the proband entity to update
     * @return the updated proband entity
     */
    Proband update(Proband proband);

    /**
     * Deletes a proband entity from the system by its ID.
     *
     * @param id the ID of the proband to delete
     */
    void delete(long id);

}
