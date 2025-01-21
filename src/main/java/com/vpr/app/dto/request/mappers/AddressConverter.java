package com.vpr.app.dto.request.mappers;

import com.vpr.app.dto.request.AddressRequestDto;
import com.vpr.app.entity.Address;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressConverter {

    public Address toEntity(AddressRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setBuilding(dto.getBuilding());
        address.setApartment(dto.getApartment());
        address.setTown(dto.getTown());

        if (dto.getId() != null) {
            address.setId(dto.getId());
        }
        return address;
    }

    public AddressRequestDto toDto(Address address) {
        if (address == null) {
            return null;
        }
        AddressRequestDto dto = new AddressRequestDto();
        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setBuilding(address.getBuilding());
        dto.setApartment(address.getApartment());
        dto.setTown(address.getTown());
        return dto;
    }
}



