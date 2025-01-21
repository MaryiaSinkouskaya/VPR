package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.PersonInfoRequestDto;
import com.vpr.app.entity.PersonInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonInfoConverter {

    private final AddressConverter addressConverter;
    private final WorkplaceConverter workplaceConverter;

    /**
     * Converts a PersonInfoRequestDto to a PersonInfo entity.
     *
     * @param dto the DTO to convert
     * @return the corresponding PersonInfo entity
     */
    public PersonInfo toEntity(PersonInfoRequestDto dto) {
        if (dto == null) {
            return null;
        }

        PersonInfo personInfo = new PersonInfo();
        personInfo.setName(dto.getName());
        personInfo.setSurname(dto.getSurname());
        personInfo.setPatronymic(dto.getPatronymic());
        personInfo.setPhone(dto.getPhone());
        personInfo.setBirthDate(dto.getBirthDate());

        if (dto.getAddress() != null) {
            personInfo.setAddress(addressConverter.toEntity(dto.getAddress()));
        }

        if (dto.getWorkplace() != null) {
            personInfo.setWorkplace(workplaceConverter.toEntity(dto.getWorkplace()));
        }

        if (dto.getId() != null) {
            personInfo.setId(dto.getId());
        }

        return personInfo;
    }

    /**
     * Converts a PersonInfo entity to a PersonInfoRequestDto.
     *
     * @param entity the entity to convert
     * @return the corresponding PersonInfoRequestDto
     */
    public PersonInfoRequestDto toDto(PersonInfo entity) {
        if (entity == null) {
            return null;
        }

        PersonInfoRequestDto dto = new PersonInfoRequestDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPatronymic(entity.getPatronymic());
        dto.setPhone(entity.getPhone());
        dto.setBirthDate(entity.getBirthDate());

        if (entity.getAddress() != null) {
            dto.setAddress(addressConverter.toDto(entity.getAddress()));
        }

        if (entity.getWorkplace() != null) {
            dto.setWorkplace(workplaceConverter.toDto(entity.getWorkplace()));
        }

        return dto;
    }
}

