package com.vpr.app.service;

import com.vpr.app.entity.Workplace;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkplaceService {
    Workplace findById(long id);

    List<Workplace> findAll();

    Workplace create(Workplace workplace);

    Workplace update(Workplace workplace);

    void delete(long id);
}
