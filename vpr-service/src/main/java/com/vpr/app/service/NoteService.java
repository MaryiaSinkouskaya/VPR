package com.vpr.app.service;

import com.vpr.app.entity.Note;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing note entities in the system.
 * Provides CRUD operations for note data.
 */
@Service
public interface NoteService {
    /**
     * Retrieves a note entity by its ID.
     *
     * @param id the ID of the note to find
     * @return the found note entity
     */
    Note findById(long id);

    /**
     * Retrieves all note entities from the system.
     *
     * @return a list of all note entities
     */
    List<Note> findAll();

    /**
     * Creates a new note entity in the system.
     *
     * @param note the note entity to create
     * @return the created note entity with generated ID
     */
    Note create(Note note);

    /**
     * Updates an existing note entity in the system.
     *
     * @param note the note entity to update
     * @return the updated note entity
     */
    Note update(Note note);

    /**
     * Deletes a note entity from the system by its ID.
     *
     * @param id the ID of the note to delete
     */
    void delete(long id);
}
