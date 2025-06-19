package com.vpr.app.service.impl;

import com.vpr.app.audit.log.annotation.AuditCreate;
import com.vpr.app.audit.log.annotation.AuditDelete;
import com.vpr.app.audit.log.annotation.AuditUpdate;
import com.vpr.app.entity.ProbandD;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.ProbandDRepository;
import com.vpr.app.service.ProbandDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link ProbandDService} interface that provides CRUD operations
 * for managing proband D entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class ProbandDServiceImpl implements ProbandDService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = ProbandD.class.getSimpleName();
    private final ProbandDRepository probandDRepository;

    /**
     * Retrieves a proband D entity by its ID.
     *
     * @param id the ID of the proband D to find
     * @return the found proband D entity
     * @throws VprEntityNotFoundException if no proband D exists with the given ID
     */
    @Override
    public ProbandD findById(long id) {
        return probandDRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all proband D entities from the system.
     *
     * @return a list of all proband D entities
     */
    @Override
    public List<ProbandD> findAll() {
        return probandDRepository.findAll();
    }

    /**
     * Creates a new proband D entity in the system.
     *
     * @param probandD the proband D entity to create
     * @return the created proband D entity with generated ID
     */
    @Override
    @AuditCreate(entity = "ProbandD")
    public ProbandD create(ProbandD probandD) {
        return probandDRepository.save(probandD);
    }

    /**
     * Updates an existing proband D entity in the system.
     *
     * @param probandD the proband D entity to update
     * @return the updated proband D entity
     */
    @Override
    @AuditUpdate(entity = "ProbandD")
    public ProbandD update(ProbandD probandD) {
        return probandDRepository.save(probandD);
    }

    /**
     * Deletes a proband D entity from the system by its ID.
     *
     * @param id the ID of the proband D to delete
     * @throws VprEntityNotFoundException if no proband D exists with the given ID
     */
    @Override
    @AuditDelete(entity = "ProbandD")
    public void delete(long id) {
        validateExistence(id);
        probandDRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of a proband D entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no proband D exists with the given ID
     */
    private void validateExistence(long id) {
        if (!probandDRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}
