package com.vpr.app.service.impl;

import com.vpr.app.entity.Abnormality;
import com.vpr.app.repository.AbnormalityRepository;
import com.vpr.app.service.AbnormalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AbnormalityServiceImpl implements AbnormalityService {

  private  final AbnormalityRepository abnormalityRepository;
  @Override
  public Abnormality findById(long id) {
    return abnormalityRepository.findById(id).orElse(new Abnormality());
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
    abnormalityRepository.deleteById(id);
  }
}
