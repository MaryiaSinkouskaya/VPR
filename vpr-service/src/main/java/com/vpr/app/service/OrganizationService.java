package com.vpr.app.service;

import com.vpr.app.entity.Organization;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing organization entities in the system.
 * Provides CRUD operations for organization data.
 */
@Service
public interface OrganizationService {
    /**
     * Retrieves an organization entity by its ID.
     *
     * @param id the ID of the organization to find
     * @return the found organization entity
     */
    Organization findById(long id);

    /**
     * Retrieves all organization entities from the system.
     *
     * @return a list of all organization entities
     */
    List<Organization> findAll();

    /**
     * Creates a new organization entity in the system.
     *
     * @param organization the organization entity to create
     * @return the created organization entity with generated ID
     */
    Organization create(Organization organization);

    /**
     * Updates an existing organization entity in the system.
     *
     * @param organization the organization entity to update
     * @return the updated organization entity
     */
    Organization update(Organization organization);

    /**
     * Deletes an organization entity from the system by its ID.
     *
     * @param id the ID of the organization to delete
     */
    void delete(long id);
}
