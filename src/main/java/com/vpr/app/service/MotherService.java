package com.vpr.app.service;

import com.vpr.app.entity.Mother;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface MotherService {

  Mother findById(long id);

  List<Mother> findAll();

  Mother create(Mother mother);

  Mother update(Mother mother);

  void delete(long id);
}
