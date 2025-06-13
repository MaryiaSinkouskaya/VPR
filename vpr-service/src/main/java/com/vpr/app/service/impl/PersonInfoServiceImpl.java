package com.vpr.app.service.impl;

import com.vpr.app.entity.PersonInfo;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.PersonInfoRepository;
import com.vpr.app.service.PersonInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link PersonInfoService} interface that provides CRUD operations
 * for managing person information entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = PersonInfo.class.getSimpleName();
    private final PersonInfoRepository personInfoRepository;

    /**
     * Retrieves a person information entity by its ID.
     *
     * @param id the ID of the person information to find
     * @return the found person information entity
     * @throws VprEntityNotFoundException if no person information exists with the given ID
     */
    @Override
    public PersonInfo findById(long id) {
        return personInfoRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all person information entities from the system.
     *
     * @return a list of all person information entities
     */
    @Override
    public List<PersonInfo> findAll() {
        return personInfoRepository.findAll();
    }

    /**
     * Creates a new person information entity in the system.
     *
     * @param personInfo the person information entity to create
     * @return the created person information entity with generated ID
     */
    @Override
    public PersonInfo create(PersonInfo personInfo) {
        return personInfoRepository.save(personInfo);
    }

    /**
     * Updates an existing person information entity in the system.
     *
     * @param personInfo the person information entity to update
     * @return the updated person information entity
     */
    @Override
    public PersonInfo update(PersonInfo personInfo) {
        return personInfoRepository.save(personInfo);
    }

    /**
     * Deletes a person information entity from the system by its ID.
     *
     * @param id the ID of the person information to delete
     * @throws VprEntityNotFoundException if no person information exists with the given ID
     */
    @Override
    public void delete(long id) {
        validateExistence(id);
        personInfoRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of a person information entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no person information exists with the given ID
     */
    private void validateExistence(long id) {
        if (!personInfoRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}