package com.vpr.app.service.impl;

import com.vpr.app.entity.ProbandD;
import com.vpr.app.repository.ProbandDRepository;
import com.vpr.app.service.ProbandDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProbandDServiceImpl implements ProbandDService {
  private final ProbandDRepository probandDRepository;

  @Override
  public ProbandD findById(long id) {
    return probandDRepository.findById(id).orElse(new ProbandD());
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
    probandDRepository.deleteById(id);
  }
}
