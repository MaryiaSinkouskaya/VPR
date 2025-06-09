package com.vpr.app.service;

import com.vpr.app.entity.Proband;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProbandService {

    Proband findById(long id);

    List<Proband> findAll();

    Proband create(Proband proband);

    Proband update(Proband proband);

    void delete(long id);

}
