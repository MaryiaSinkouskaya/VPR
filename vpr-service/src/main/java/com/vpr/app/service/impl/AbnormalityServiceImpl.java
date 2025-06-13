package com.vpr.app.service.impl;

import com.vpr.app.entity.Abnormality;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.AbnormalityRepository;
import com.vpr.app.service.AbnormalityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link AbnormalityService} interface that provides CRUD operations
 * for managing abnormality entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class AbnormalityServiceImpl implements AbnormalityService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Abnormality.class.getSimpleName();
    private final AbnormalityRepository abnormalityRepository;

    /**
     * Retrieves an abnormality entity by its ID.
     *
     * @param id the ID of the abnormality to find
     * @return the found abnormality entity
     * @throws VprEntityNotFoundException if no abnormality exists with the given ID
     */
    @Override
    public Abnormality findById(long id) {
        return abnormalityRepository.findById(id)//
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all abnormality entities from the system.
     *
     * @return a list of all abnormality entities
     */
    @Override
    public List<Abnormality> findAll() {
        return abnormalityRepository.findAll();
    }

    /**
     * Creates a new abnormality entity in the system.
     *
     * @param abnormality the abnormality entity to create
     * @return the created abnormality entity with generated ID
     */
    @Override
    public Abnormality create(Abnormality abnormality) {
        return abnormalityRepository.save(abnormality);
    }

    /**
     * Updates an existing abnormality entity in the system.
     *
     * @param abnormality the abnormality entity to update
     * @return the updated abnormality entity
     */
    @Override
    public Abnormality update(Abnormality abnormality) {
        return abnormalityRepository.save(abnormality);
    }

    /**
     * Deletes an abnormality entity from the system by its ID.
     *
     * @param id the ID of the abnormality to delete
     * @throws VprEntityNotFoundException if no abnormality exists with the given ID
     */
    @Override
    public void delete(long id) {
        validateExistence(id);
        abnormalityRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of an abnormality entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no abnormality exists with the given ID
     */
    private void validateExistence(long id) {
        if (!abnormalityRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}",
                    Abnormality.class.getSimpleName(), id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}
