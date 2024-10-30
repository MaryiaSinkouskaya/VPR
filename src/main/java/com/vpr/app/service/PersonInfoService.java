package com.vpr.app.service;

import com.vpr.app.entity.PersonInfo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PersonInfoService {
  PersonInfo findById(long id);

  List<PersonInfo> findAll();

  PersonInfo create(PersonInfo personInfoe);

  PersonInfo update(PersonInfo personInfo);

  void delete(long id);
}
