package com.vpr.app.service.impl;

import com.vpr.app.entity.Note;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.NoteRepository;
import com.vpr.app.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link NoteService} interface that provides CRUD operations
 * for managing note entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class NoteServiceImpl implements NoteService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Note.class.getSimpleName();
    private final NoteRepository noteRepository;

    /**
     * Retrieves a note entity by its ID.
     *
     * @param id the ID of the note to find
     * @return the found note entity
     * @throws VprEntityNotFoundException if no note exists with the given ID
     */
    @Override
    public Note findById(long id) {
        return noteRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all note entities from the system.
     *
     * @return a list of all note entities
     */
    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    /**
     * Creates a new note entity in the system.
     *
     * @param note the note entity to create
     * @return the created note entity with generated ID
     */
    @Override
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Updates an existing note entity in the system.
     *
     * @param note the note entity to update
     * @return the updated note entity
     */
    @Override
    public Note update(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Deletes a note entity from the system by its ID.
     *
     * @param id the ID of the note to delete
     * @throws VprEntityNotFoundException if no note exists with the given ID
     */
    @Override
    public void delete(long id) {
        validateExistence(id);
        noteRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of a note entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no note exists with the given ID
     */
    private void validateExistence(long id) {
        if (!noteRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}

