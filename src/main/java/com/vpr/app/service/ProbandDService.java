package com.vpr.app.service;

import com.vpr.app.entity.ProbandD;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProbandDService {
    ProbandD findById(long id);

    List<ProbandD> findAll();

    ProbandD create(ProbandD probandD);

    ProbandD update(ProbandD probandD);

    void delete(long id);
}
