package com.vpr.app.service;

import com.vpr.app.entity.Abnormality;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AbnormalityService {
  Abnormality findById(long id);

  List<Abnormality> findAll();

  Abnormality create(Abnormality abnormality);

  Abnormality update(Abnormality abnormality);

  void delete(long id);
}
