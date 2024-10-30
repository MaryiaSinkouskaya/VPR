package com.vpr.app.service;

import com.vpr.app.entity.Doctor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface DoctorService {
  Doctor findById(long id);

  List<Doctor> findAll();

  Doctor create(Doctor doctor);

  Doctor update(Doctor doctor);

  void delete(long id);
}
