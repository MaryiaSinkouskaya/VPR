package com.vpr.app.service;

import com.vpr.app.entity.ProbandD;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing proband D entities in the system.
 * Provides CRUD operations for proband D data.
 */
@Service
public interface ProbandDService {
    /**
     * Retrieves a proband D entity by its ID.
     *
     * @param id the ID of the proband D to find
     * @return the found proband D entity
     */
    ProbandD findById(long id);

    /**
     * Retrieves all proband D entities from the system.
     *
     * @return a list of all proband D entities
     */
    List<ProbandD> findAll();

    /**
     * Creates a new proband D entity in the system.
     *
     * @param probandD the proband D entity to create
     * @return the created proband D entity with generated ID
     */
    ProbandD create(ProbandD probandD);

    /**
     * Updates an existing proband D entity in the system.
     *
     * @param probandD the proband D entity to update
     * @return the updated proband D entity
     */
    ProbandD update(ProbandD probandD);

    /**
     * Deletes a proband D entity from the system by its ID.
     *
     * @param id the ID of the proband D to delete
     */
    void delete(long id);
}
