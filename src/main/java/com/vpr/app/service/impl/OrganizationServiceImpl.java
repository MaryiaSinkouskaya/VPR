package com.vpr.app.service.impl;

import com.vpr.app.entity.Organization;
import com.vpr.app.repository.OrganizationRepository;
import com.vpr.app.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {
  private final OrganizationRepository organizationRepository;

  @Override
  public Organization findById(long id) {
    return organizationRepository.findById(id).orElse(new Organization());
  }

  @Override
  public List<Organization> findAll() {
    return organizationRepository.findAll();
  }

  @Override
  public Organization create(Organization organization) {
    return organizationRepository.save(organization);
  }

  @Override
  public Organization update(Organization organization) {
    return organizationRepository.save(organization);
  }

  @Override
  public void delete(long id) {
    organizationRepository.deleteById(id);
  }
}
