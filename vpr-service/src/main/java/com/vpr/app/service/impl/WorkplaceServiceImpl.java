package com.vpr.app.service.impl;

import com.vpr.app.audit.log.annotation.AuditCreate;
import com.vpr.app.audit.log.annotation.AuditDelete;
import com.vpr.app.audit.log.annotation.AuditUpdate;
import com.vpr.app.entity.Workplace;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.WorkplaceRepository;
import com.vpr.app.service.WorkplaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link WorkplaceService} interface that provides CRUD operations
 * for managing workplace entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class WorkplaceServiceImpl implements WorkplaceService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Workplace.class.getSimpleName();
    private final WorkplaceRepository workplaceRepository;

    /**
     * Retrieves a workplace entity by its ID.
     *
     * @param id the ID of the workplace to find
     * @return the found workplace entity
     * @throws VprEntityNotFoundException if no workplace exists with the given ID
     */
    @Override
    public Workplace findById(long id) {
        return workplaceRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all workplace entities from the system.
     *
     * @return a list of all workplace entities
     */
    @Override
    public List<Workplace> findAll() {
        return workplaceRepository.findAll();
    }

    /**
     * Creates a new workplace entity in the system.
     *
     * @param workplace the workplace entity to create
     * @return the created workplace entity with generated ID
     */
    @Override
    @AuditCreate(entity = "Workplace")
    public Workplace create(Workplace workplace) {
        return workplaceRepository.save(workplace);
    }

    /**
     * Updates an existing workplace entity in the system.
     *
     * @param workplace the workplace entity to update
     * @return the updated workplace entity
     */
    @Override
    @AuditUpdate(entity = "Workplace")
    public Workplace update(Workplace workplace) {
        return workplaceRepository.save(workplace);
    }

    /**
     * Deletes a workplace entity from the system by its ID.
     *
     * @param id the ID of the workplace to delete
     * @throws VprEntityNotFoundException if no workplace exists with the given ID
     */
    @Override
    @AuditDelete(entity = "Workplace")
    public void delete(long id) {
        validateExistence(id);
        workplaceRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of a workplace entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no workplace exists with the given ID
     */
    private void validateExistence(long id) {
        if (!workplaceRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}

