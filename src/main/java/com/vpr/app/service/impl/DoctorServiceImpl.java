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

@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class DoctorServiceImpl implements DoctorService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Doctor.class.getSimpleName();
    private final DoctorRepository doctorRepository;

    @Override
    public Doctor findById(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void delete(long id) {
        validateExistence(id);
        doctorRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    private void validateExistence(long id) {
        if (!doctorRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}
