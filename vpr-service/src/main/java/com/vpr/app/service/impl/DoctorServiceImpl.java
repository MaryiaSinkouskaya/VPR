package com.vpr.app.service.impl;

import com.vpr.app.entity.Doctor;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.DoctorRepository;
import com.vpr.app.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link DoctorService} interface that provides CRUD operations
 * for managing doctor entities in the system.
 * This service is restricted to users with ADMIN, DOCTOR, or VIEWER roles.
 */
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class DoctorServiceImpl implements DoctorService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Doctor.class.getSimpleName();
    private final DoctorRepository doctorRepository;

    /**
     * Retrieves a doctor entity by its ID.
     *
     * @param id the ID of the doctor to find
     * @return the found doctor entity
     * @throws VprEntityNotFoundException if no doctor exists with the given ID
     */
    @Override
    public Doctor findById(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    /**
     * Retrieves all doctor entities from the system.
     *
     * @return a list of all doctor entities
     */
    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    /**
     * Creates a new doctor entity in the system.
     *
     * @param doctor the doctor entity to create
     * @return the created doctor entity with generated ID
     */
    @Override
    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    /**
     * Updates an existing doctor entity in the system.
     *
     * @param doctor the doctor entity to update
     * @return the updated doctor entity
     */
    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    /**
     * Deletes a doctor entity from the system by its ID.
     *
     * @param id the ID of the doctor to delete
     * @throws VprEntityNotFoundException if no doctor exists with the given ID
     */
    @Override
    public void delete(long id) {
        validateExistence(id);
        doctorRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    /**
     * Validates the existence of a doctor entity with the given ID.
     *
     * @param id the ID to validate
     * @throws VprEntityNotFoundException if no doctor exists with the given ID
     */
    private void validateExistence(long id) {
        if (!doctorRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}
