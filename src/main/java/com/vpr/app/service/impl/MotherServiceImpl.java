package com.vpr.app.service.impl;

import com.vpr.app.entity.Mother;
import com.vpr.app.repository.MotherRepository;
import com.vpr.app.service.MotherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MotherServiceImpl implements MotherService {

  private final MotherRepository motherRepository;

  @Override
  public Mother findById(long id) {
    return motherRepository.findById(id).orElse(new Mother());
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
    return motherRepository.save(mother);  }

  @Override
  public void delete(long id) {
    motherRepository.deleteById(id);
  }
}
