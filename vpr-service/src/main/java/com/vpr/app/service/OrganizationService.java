package com.vpr.app.service;

import com.vpr.app.entity.Organization;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationService {
    Organization findById(long id);

    List<Organization> findAll();

    Organization create(Organization organization);

    Organization update(Organization organization);

    void delete(long id);
}
