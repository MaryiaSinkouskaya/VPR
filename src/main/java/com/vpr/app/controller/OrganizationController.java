package com.vpr.app.controller;

import com.vpr.app.entity.Organization;
import com.vpr.app.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
  private final OrganizationService organizationService;

  @GetMapping()
  public List<Organization> getOrganizations() {
    return organizationService.findAll();
  }

  @GetMapping(value = "/{id}")
  public Organization getOrganizationById(@PathVariable(name = "id") long id) {
    return organizationService.findById(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Organization createOrganization(@RequestBody Organization organization) {
    return organizationService.create(organization);
  }

  @PutMapping()
  public Organization updateOrganization(@RequestBody Organization organization) {
    return organizationService.update(organization);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteOrganizationById(@PathVariable(name = "id") long id) {
    organizationService.delete(id);
  }
}
