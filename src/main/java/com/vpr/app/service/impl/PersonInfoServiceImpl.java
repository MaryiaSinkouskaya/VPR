package com.vpr.app.service.impl;

import com.vpr.app.entity.PersonInfo;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.PersonInfoRepository;
import com.vpr.app.service.PersonInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
    public static final String ENTITY_NAME = PersonInfo.class.getSimpleName();
    private final PersonInfoRepository personInfoRepository;

    @Override
    public PersonInfo findById(long id) {
        return personInfoRepository.findById(id)
                .orElseThrow(
                        () -> new VprEntityNotFoundException(
                                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
    }

    @Override
    public List<PersonInfo> findAll() {
        return personInfoRepository.findAll();
    }

    @Override
    public PersonInfo create(PersonInfo personInfo) {
        return personInfoRepository.save(personInfo);
    }

    @Override
    public PersonInfo update(PersonInfo personInfo) {
        return personInfoRepository.save(personInfo);
    }

    @Override
    public void delete(long id) {
        validateExistence(id);
        personInfoRepository.deleteById(id);
        log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
    }

    private void validateExistence(long id) {
        if (!personInfoRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
            throw new VprEntityNotFoundException(
                    String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
        }
    }
}