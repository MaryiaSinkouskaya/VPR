package com.vpr.app.service.impl;

import com.vpr.app.entity.PersonInfo;
import com.vpr.app.repository.PersonInfoRepository;
import com.vpr.app.service.PersonInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonInfoServiceImpl implements PersonInfoService {
  private final PersonInfoRepository personInfoRepository;

  @Override
  public PersonInfo findById(long id) {
    return personInfoRepository.findById(id).orElse(new PersonInfo());
  }

  @Override
  public List<PersonInfo> findAll() {
    return personInfoRepository.findAll();
  }

  @Override
  public PersonInfo create(PersonInfo personInfoe) {
    return personInfoRepository.save(personInfoe);
  }

  @Override
  public PersonInfo update(PersonInfo personInfo) {
    return personInfoRepository.save(personInfo);
  }

  @Override
  public void delete(long id) {
    personInfoRepository.deleteById(id);
  }
}
