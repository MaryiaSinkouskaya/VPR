package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.OrganizationRequestDto;
import com.vpr.app.entity.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationConverter {

    /**
     * Converts OrganizationRequestDto to Organization entity.
     *
     * @param dto the OrganizationRequestDto object
     * @return the converted Organization entity
     */
    public Organization toEntity(OrganizationRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Organization organization = new Organization();
        if (dto.getId() != null) {
            organization.setId(dto.getId());
        }
        organization.setNumber(dto.getNumber());
        organization.setName(dto.getName());
        return organization;
    }

    /**
     * Converts Organization entity to OrganizationRequestDto.
     *
     * @param organization the Organization entity
     * @return the converted OrganizationRequestDto object
     */
    public OrganizationRequestDto toDto(Organization organization) {
        if (organization == null) {
            return null;
        }

        OrganizationRequestDto dto = new OrganizationRequestDto();
        dto.setId(organization.getId());
        dto.setNumber(organization.getNumber());
        dto.setName(organization.getName());
        return dto;
    }

}
