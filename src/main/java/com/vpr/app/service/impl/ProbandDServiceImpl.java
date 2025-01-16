package com.vpr.app.service.impl;

import com.vpr.app.entity.ProbandD;
import com.vpr.app.exceptions.VprEntityNotFoundException;
import com.vpr.app.repository.ProbandDRepository;
import com.vpr.app.service.ProbandDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProbandDServiceImpl implements ProbandDService {

  public static final String INSTANCE_DOES_NOT_EXIST = "%s instance with id %d does not exist";
  public static final String ENTITY_NAME = ProbandD.class.getSimpleName();
  private final ProbandDRepository probandDRepository;

  @Override
  public ProbandD findById(long id) {
    return probandDRepository.findById(id)
        .orElseThrow(
            () -> new VprEntityNotFoundException(
                String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id)));
  }

  @Override
  public List<ProbandD> findAll() {
    return probandDRepository.findAll();
  }

  @Override
  public ProbandD create(ProbandD probandD) {
    return probandDRepository.save(probandD);
  }

  @Override
  public ProbandD update(ProbandD probandD) {
    return probandDRepository.save(probandD);
  }

  @Override
  public void delete(long id) {
    validateExistence(id);
    probandDRepository.deleteById(id);
    log.info("Successfully deleted {} entity with id {}", ENTITY_NAME, id);
  }

  private void validateExistence(long id) {
    if (!probandDRepository.existsById(id)) {
      log.warn("Attempted to delete non-existent {} entity with id {}", ENTITY_NAME, id);
      throw new VprEntityNotFoundException(
          String.format(INSTANCE_DOES_NOT_EXIST, ENTITY_NAME, id));
    }
  }
}
