package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.WorkplaceRequestDto;
import com.vpr.app.entity.Workplace;
import org.springframework.stereotype.Component;

@Component
public class WorkplaceConverter {

    public Workplace toEntity(WorkplaceRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Workplace workplace = new Workplace();
        workplace.setJobType(dto.getJobType());
        workplace.setCompany(dto.getCompany());

        if (dto.getId() != null) {
            workplace.setId(dto.getId());
        }

        return workplace;
    }

    public WorkplaceRequestDto toDto(Workplace workplace) {
        if (workplace == null) {
            return null;
        }
        WorkplaceRequestDto dto = new WorkplaceRequestDto();
        dto.setId(workplace.getId());
        dto.setJobType(workplace.getJobType());
        dto.setCompany(workplace.getCompany());
        return dto;
    }
}
