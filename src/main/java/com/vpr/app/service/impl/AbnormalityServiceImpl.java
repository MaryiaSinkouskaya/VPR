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

@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR') or hasRole('VIEWER')")
@Service
public class AbnormalityServiceImpl implements AbnormalityService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = Abnormality.class.getSimpleName();
    private final AbnormalityRepository abnormalityRepository;

    @Override
    public Abnormality findById(long id) {
        return abnormalityRepository.findById(id)//
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    @Override
    public List<Abnormality> findAll() {
        return abnormalityRepository.findAll();
    }

    @Override
    public Abnormality create(Abnormality abnormality) {
        return abnormalityRepository.save(abnormality);
    }

    @Override
    public Abnormality update(Abnormality abnormality) {
        return abnormalityRepository.save(abnormality);
    }

    @Override
    public void delete(long id) {
        validateExistence(id);
        abnormalityRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    private void validateExistence(long id) {
        if (!abnormalityRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}",
                    Abnormality.class.getSimpleName(), id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}
