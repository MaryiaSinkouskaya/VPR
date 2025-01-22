package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.MotherRequestDto;
import com.vpr.app.entity.Mother;
import org.springframework.stereotype.Component;

@Component
public class MotherConverter {

    private final PersonInfoConverter personInfoConverter;

    public MotherConverter(PersonInfoConverter personInfoConverter) {
        this.personInfoConverter = personInfoConverter;
    }

    /**
     * Converts MotherRequestDto to Mother entity.
     *
     * @param dto the MotherRequestDto object
     * @return the converted Mother entity
     */
    public Mother toEntity(MotherRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Mother mother = new Mother();
        if (dto.getId() != null) {
            mother.setId(dto.getId());
        }
        mother.setLastMenstruationDate(dto.getLastMenstruationDate());
        mother.setDiagnoseDate(dto.getDiagnoseDate());
        mother.setGirlSurname(dto.getGirlSurname());
        mother.setPersonInfo(personInfoConverter.toEntity(dto.getPersonInfo()));
        return mother;
    }

    /**
     * Converts Mother entity to MotherRequestDto.
     *
     * @param mother the Mother entity
     * @return the converted MotherRequestDto object
     */
    public MotherRequestDto toDto(Mother mother) {
        if (mother == null) {
            return null;
        }

        MotherRequestDto dto = new MotherRequestDto();
        dto.setId(mother.getId()); // Directly map Long id
        dto.setLastMenstruationDate(mother.getLastMenstruationDate());
        dto.setDiagnoseDate(mother.getDiagnoseDate());
        dto.setGirlSurname(mother.getGirlSurname());
        dto.setPersonInfo(personInfoConverter.toDto(mother.getPersonInfo()));
        return dto;
    }

}
