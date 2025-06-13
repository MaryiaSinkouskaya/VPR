package com.vpr.app.service.impl;

import com.vpr.app.entity.Mother;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.MotherRepository;
import com.vpr.app.service.MotherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link MotherService} interface that provides CRUD operations
 * for managing mother entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class MotherServiceImpl implements MotherService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Mother.class.getSimpleName();
    private final MotherRepository motherRepository;

    /**
     * Retrieves a mother entity by its ID.
     *
     * @param id the ID of the mother to find
     * @return the found mother entity
     * @throws VprEntityNotFoundException if no mother exists with the given ID
     */
    @Override
    public Mother findById(long id) {
        return motherRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all mother entities from the system.
     *
     * @return a list of all mother entities
     */
    @Override
    public List<Mother> findAll() {
        return motherRepository.findAll();
    }

    /**
     * Creates a new mother entity in the system.
     *
     * @param mother the mother entity to create
     * @return the created mother entity with generated ID
     */
    @Override
    public Mother create(Mother mother) {
        return motherRepository.save(mother);
    }

    /**
     * Updates an existing mother entity in the system.
     *
     * @param mother the mother entity to update
     * @return the updated mother entity
     */
    @Override
    public Mother update(Mother mother) {
        return motherRepository.save(mother);
    }

    /**
     * Deletes a mother entity from the system by its ID.
     *
     * @param id the ID of the mother to delete
     * @throws VprEntityNotFoundException if no mother exists with the given ID
     */
    @Override
    public void delete(long id) {
        validateExistence(id);
        motherRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of a mother entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no mother exists with the given ID
     */
    private void validateExistence(long id) {
        if (!motherRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}

