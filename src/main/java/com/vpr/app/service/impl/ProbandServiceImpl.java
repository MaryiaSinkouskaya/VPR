package com.vpr.app.service.impl;

import com.vpr.app.entity.Proband;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.ProbandRepository;
import com.vpr.app.service.ProbandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProbandServiceImpl implements ProbandService {

  public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
  public static final String ENTITY_NAME = Proband.class.getSimpleName();
  private final ProbandRepository probandRepository;

  @Override
  public Proband findById(long id) {
    return probandRepository.findById(id)
        .orElseThrow(
            () -> new VprEntityNotFoundException(
                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
  }

  @Override
  public List<Proband> findAll() {
    return probandRepository.findAll();
  }

  @Override
  public Proband create(Proband proband) {
    return probandRepository.save(proband);
  }

  @Override
  public Proband update(Proband proband) {
    return probandRepository.save(proband);
  }

  @Override
  public void delete(long id) {
    validateExistence(id);
    probandRepository.deleteById(id);
    log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
  }

  private void validateExistence(long id) {
    if (!probandRepository.existsById(id)) {
      log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
      throw new VprEntityNotFoundException(
          String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
    }
  }
}

