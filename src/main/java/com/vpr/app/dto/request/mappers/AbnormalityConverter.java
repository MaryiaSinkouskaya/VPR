package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.AbnormalityRequestDto;
import com.vpr.app.entity.Abnormality;
import org.springframework.stereotype.Component;

@Component
public class AbnormalityConverter {

    /**
     * Converts AbnormalityRequestDto to Abnormality entity.
     *
     * @param dto the AbnormalityRequestDto object
     * @return the converted Abnormality entity
     */
    public Abnormality toEntity(AbnormalityRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Abnormality abnormality = new Abnormality();
        if (dto.getId() != null) {
            abnormality.setId(dto.getId());
        }
        abnormality.setName(dto.getName());
        return abnormality;
    }

    /**
     * Converts Abnormality entity to AbnormalityRequestDto.
     *
     * @param abnormality the Abnormality entity
     * @return the converted AbnormalityRequestDto object
     */
    public AbnormalityRequestDto toDto(Abnormality abnormality) {
        if (abnormality == null) {
            return null;
        }

        AbnormalityRequestDto dto = new AbnormalityRequestDto();
        if (abnormality.getId() != null) {
            dto.setId(abnormality.getId());
        }
        dto.setName(abnormality.getName());
        return dto;
    }
}