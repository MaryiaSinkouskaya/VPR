package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.DoctorRequestDto;
import com.vpr.app.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorConverter {

    private final PersonInfoConverter personInfoConverter;

    public DoctorConverter(PersonInfoConverter personInfoConverter) {
        this.personInfoConverter = personInfoConverter;
    }

    /**
     * Converts DoctorRequestDto to Doctor entity.
     *
     * @param dto the DoctorRequestDto object
     * @return the converted Doctor entity
     */
    public Doctor toEntity(DoctorRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        if (dto.getId() != null) {
            doctor.setId(dto.getId());
        }
        doctor.setSpeciality(dto.getSpeciality());
        doctor.setPersonInfo(personInfoConverter.toEntity(dto.getPersonInfo()));
        return doctor;
    }

    /**
     * Converts Doctor entity to DoctorRequestDto.
     *
     * @param doctor the Doctor entity
     * @return the converted DoctorRequestDto object
     */
    public DoctorRequestDto toDto(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        DoctorRequestDto dto = new DoctorRequestDto();
        dto.setId(doctor.getId());
        dto.setSpeciality(doctor.getSpeciality());
        dto.setPersonInfo(personInfoConverter.toDto(doctor.getPersonInfo()));
        return dto;
    }
}
