package com.vpr.app.service.impl;

import com.vpr.app.entity.Organization;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.OrganizationRepository;
import com.vpr.app.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link OrganizationService} interface that provides CRUD operations
 * for managing organization entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class OrganizationServiceImpl implements OrganizationService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Organization.class.getSimpleName();
    private final OrganizationRepository organizationRepository;

    /**
     * Retrieves an organization entity by its ID.
     *
     * @param id the ID of the organization to find
     * @return the found organization entity
     * @throws VprEntityNotFoundException if no organization exists with the given ID
     */
    @Override
    public Organization findById(long id) {
        return organizationRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all organization entities from the system.
     *
     * @return a list of all organization entities
     */
    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    /**
     * Creates a new organization entity in the system.
     *
     * @param organization the organization entity to create
     * @return the created organization entity with generated ID
     */
    @Override
    public Organization create(Organization organization) {
        return organizationRepository.save(organization);
    }

    /**
     * Updates an existing organization entity in the system.
     *
     * @param organization the organization entity to update
     * @return the updated organization entity
     */
    @Override
    public Organization update(Organization organization) {
        return organizationRepository.save(organization);
    }

    /**
     * Deletes an organization entity from the system by its ID.
     *
     * @param id the ID of the organization to delete
     * @throws VprEntityNotFoundException if no organization exists with the given ID
     */
    @Override
    public void delete(long id) {
        validateExistence(id);
        organizationRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of an organization entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no organization exists with the given ID
     */
    private void validateExistence(long id) {
        if (!organizationRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}

