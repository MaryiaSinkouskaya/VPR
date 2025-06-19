package com.vpr.app.service.impl;

import com.vpr.app.audit.log.annotation.AuditCreate;
import com.vpr.app.audit.log.annotation.AuditDelete;
import com.vpr.app.audit.log.annotation.AuditUpdate;
import com.vpr.app.entity.Proband;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.ProbandRepository;
import com.vpr.app.service.ProbandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link ProbandService} interface that provides CRUD operations
 * for managing proband entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class ProbandServiceImpl implements ProbandService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Proband.class.getSimpleName();
    private final ProbandRepository probandRepository;

    /**
     * Retrieves a proband entity by its ID.
     *
     * @param id the ID of the proband to find
     * @return the found proband entity
     * @throws VprEntityNotFoundException if no proband exists with the given ID
     */
    @Override
    public Proband findById(long id) {
        return probandRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all proband entities from the system.
     *
     * @return a list of all proband entities
     */
    @Override
    public List<Proband> findAll() {
        return probandRepository.findAll();
    }

    /**
     * Creates a new proband entity in the system.
     *
     * @param proband the proband entity to create
     * @return the created proband entity with generated ID
     */
    @Override
    @AuditCreate(entity = "Proband")
    public Proband create(Proband proband) {
        return probandRepository.save(proband);
    }

    /**
     * Updates an existing proband entity in the system.
     *
     * @param proband the proband entity to update
     * @return the updated proband entity
     */
    @Override
    @AuditUpdate(entity = "Proband")
    public Proband update(Proband proband) {
        return probandRepository.save(proband);
    }

    /**
     * Deletes a proband entity from the system by its ID.
     *
     * @param id the ID of the proband to delete
     * @throws VprEntityNotFoundException if no proband exists with the given ID
     */
    @Override
    @AuditDelete(entity = "Proband")
    public void delete(long id) {
        validateExistence(id);
        probandRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of a proband entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no proband exists with the given ID
     */
    private void validateExistence(long id) {
        if (!probandRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}

