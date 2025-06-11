package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.ProbandDRequestDto;
import com.vpr.app.entity.ProbandD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProbandDConverter {

    private final ProbandConverter probandConverter;

    /**
     * Converts ProbandDRequestDto to ProbandD entity.
     *
     * @param dto the ProbandDRequestDto object
     * @return the converted ProbandD entity
     */
    public ProbandD toEntity(ProbandDRequestDto dto) {
        if (dto == null) {
            return null;
        }

        ProbandD probandD = new ProbandD();
        if (dto.getId() != null) {
            probandD.setId(dto.getId());
        }
        probandD.setDeathDate(dto.getDeathDate());

        probandD.setProband(probandConverter.toEntity(dto.getProband()));

        return probandD;
    }

    /**
     * Converts ProbandD entity to ProbandDRequestDto.
     *
     * @param entity the ProbandD entity
     * @return the converted ProbandDRequestDto object
     */
    public ProbandDRequestDto toDto(ProbandD entity) {
        if (entity == null) {
            return null;
        }

        ProbandDRequestDto dto = new ProbandDRequestDto();
        dto.setId(entity.getId());
        dto.setDeathDate(entity.getDeathDate());

        dto.setProband(probandConverter.toDto(entity.getProband()));

        return dto;
    }
}

