package com.vpr.app.service.impl;

import com.vpr.app.entity.Mother;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.MotherRepository;
import com.vpr.app.service.MotherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class MotherServiceImpl implements MotherService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Mother.class.getSimpleName();
    private final MotherRepository motherRepository;

    @Override
    public Mother findById(long id) {
        return motherRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    @Override
    public List<Mother> findAll() {
        return motherRepository.findAll();
    }

    @Override
    public Mother create(Mother mother) {
        return motherRepository.save(mother);
    }

    @Override
    public Mother update(Mother mother) {
        return motherRepository.save(mother);
    }

    @Override
    public void delete(long id) {
        validateExistence(id);
        motherRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    private void validateExistence(long id) {
        if (!motherRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}

