package com.vpr.app.service.impl;

import com.vpr.app.entity.Workplace;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.WorkplaceRepository;
import com.vpr.app.service.WorkplaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class WorkplaceServiceImpl implements WorkplaceService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Workplace.class.getSimpleName();
    private final WorkplaceRepository workplaceRepository;

    @Override
    public Workplace findById(long id) {
        return workplaceRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    @Override
    public List<Workplace> findAll() {
        return workplaceRepository.findAll();
    }

    @Override
    public Workplace create(Workplace workplace) {
        return workplaceRepository.save(workplace);
    }

    @Override
    public Workplace update(Workplace workplace) {
        return workplaceRepository.save(workplace);
    }

    @Override
    public void delete(long id) {
        validateExistence(id);
        workplaceRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    private void validateExistence(long id) {
        if (!workplaceRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}

