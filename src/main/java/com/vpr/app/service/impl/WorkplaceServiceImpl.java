package com.vpr.app.service.impl;

import com.vpr.app.entity.Workplace;
import com.vpr.app.repository.WorkplaceRepository;
import com.vpr.app.service.WorkplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkplaceServiceImpl implements WorkplaceService {
  private final WorkplaceRepository workplaceRepository;

  @Override
  public Workplace findById(long id) {
    return workplaceRepository.findById(id).orElse(new Workplace());
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
    workplaceRepository.deleteById(id);
  }
}
