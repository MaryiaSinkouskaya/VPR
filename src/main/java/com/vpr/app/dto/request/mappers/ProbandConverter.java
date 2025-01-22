package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.ProbandRequestDto;
import com.vpr.app.entity.Proband;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProbandConverter {

    private final NoteConverter noteConverter;
    private final PersonInfoConverter personInfoConverter;
    private final OrganizationConverter organizationConverter;
    private final MotherConverter motherConverter;
    private final AbnormalityConverter abnormalityConverter;


    /**
     * Converts ProbandRequestDto to Proband entity.
     *
     * @param dto the ProbandRequestDto object
     * @return the converted Proband entity
     */
    public Proband toEntity(ProbandRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Proband proband = new Proband();
        if (dto.getId() != null) {
            proband.setId(dto.getId());
        }
        proband.setBirthDate(dto.getBirthDate());
        proband.setKaryotype(dto.getKaryotype());
        proband.setPregnancyDurationInWeeks(dto.getPregnancyDurationInWeeks());
        proband.setWeight(dto.getWeight());
        proband.setHead(dto.getHead());
        proband.setPregnancyNumber(dto.getPregnancyNumber());
        proband.setAborted(dto.isAborted());
        proband.setGender(dto.getGender());
        proband.setPloid(dto.getPloid());
        proband.setLaborOutcome(dto.getLaborOutcome());

        proband.setNote(noteConverter.toEntity(dto.getNote()));
        proband.setFather(personInfoConverter.toEntity(dto.getFather()));
        proband.setOrganization(organizationConverter.toEntity(dto.getOrganization()));
        proband.setMother(motherConverter.toEntity(dto.getMother()));
        proband.setAbnormality(abnormalityConverter.toEntity(dto.getAbnormality()));

        return proband;
    }

    /**
     * Converts Proband entity to ProbandRequestDto.
     *
     * @param proband the Proband entity
     * @return the converted ProbandRequestDto object
     */
    public ProbandRequestDto toDto(Proband proband) {
        if (proband == null) {
            return null;
        }

        ProbandRequestDto dto = new ProbandRequestDto();
        dto.setId(proband.getId());
        dto.setBirthDate(proband.getBirthDate());
        dto.setKaryotype(proband.getKaryotype());
        dto.setPregnancyDurationInWeeks(proband.getPregnancyDurationInWeeks());
        dto.setWeight(proband.getWeight());
        dto.setHead(proband.getHead());
        dto.setPregnancyNumber(proband.getPregnancyNumber());
        dto.setAborted(proband.isAborted());
        dto.setGender(proband.getGender());
        dto.setPloid(proband.getPloid());
        dto.setLaborOutcome(proband.getLaborOutcome());

        // Map nested entities to DTOs
        dto.setNote(noteConverter.toDto(proband.getNote()));
        dto.setFather(personInfoConverter.toDto(proband.getFather()));
        dto.setOrganization(organizationConverter.toDto(proband.getOrganization()));
        dto.setMother(motherConverter.toDto(proband.getMother()));
        dto.setAbnormality(abnormalityConverter.toDto(proband.getAbnormality()));

        return dto;
    }
}

