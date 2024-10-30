package com.vpr.app.service.impl;

import com.vpr.app.entity.Doctor;
import com.vpr.app.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {


  @Override
  public Doctor findById(long id) {
    return null;
  }

  @Override
  public List<Doctor> findAll() {
    return null;
  }

  @Override
  public Doctor create(Doctor doctor) {
    return null;
  }

  @Override
  public Doctor update(Doctor doctor) {
    return null;
  }

  @Override
  public void delete(long id) {

  }
}
