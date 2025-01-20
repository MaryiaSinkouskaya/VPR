package com.vpr.app.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class PersonInfoRequestDto {

    private String name;

    private String surname;

    private String patronymic;

    private String phone;

    private Date birthDate;

    private AddressRequestDto address;

    private WorkplaceRequestDto workplace;
}
