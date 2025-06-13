package com.vpr.app.service;

import com.vpr.app.entity.PersonInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing person information entities in the system.
 * Provides CRUD operations for person information data.
 */
@Service
public interface PersonInfoService {
    /**
     * Retrieves a person information entity by its ID.
     *
     * @param id the ID of the person information to find
     * @return the found person information entity
     */
    PersonInfo findById(long id);

    /**
     * Retrieves all person information entities from the system.
     *
     * @return a list of all person information entities
     */
    List<PersonInfo> findAll();

    /**
     * Creates a new person information entity in the system.
     *
     * @param personInfoe the person information entity to create
     * @return the created person information entity with generated ID
     */
    PersonInfo create(PersonInfo personInfoe);

    /**
     * Updates an existing person information entity in the system.
     *
     * @param personInfo the person information entity to update
     * @return the updated person information entity
     */
    PersonInfo update(PersonInfo personInfo);

    /**
     * Deletes a person information entity from the system by its ID.
     *
     * @param id the ID of the person information to delete
     */
    void delete(long id);
}
