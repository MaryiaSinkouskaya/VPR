package com.vpr.app.service;

import com.vpr.app.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing doctor entities in the system.
 * Provides CRUD operations for doctor data.
 */
@Service
public interface DoctorService {
    /**
     * Retrieves a doctor entity by its ID.
     *
     * @param id the ID of the doctor to find
     * @return the found doctor entity
     */
    Doctor findById(long id);

    /**
     * Retrieves all doctor entities from the system.
     *
     * @return a list of all doctor entities
     */
    List<Doctor> findAll();

    /**
     * Creates a new doctor entity in the system.
     *
     * @param doctor the doctor entity to create
     * @return the created doctor entity with generated ID
     */
    Doctor create(Doctor doctor);

    /**
     * Updates an existing doctor entity in the system.
     *
     * @param doctor the doctor entity to update
     * @return the updated doctor entity
     */
    Doctor update(Doctor doctor);

    /**
     * Deletes a doctor entity from the system by its ID.
     *
     * @param id the ID of the doctor to delete
     */
    void delete(long id);
}
