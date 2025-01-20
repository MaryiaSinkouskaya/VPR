package com.vpr.app.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class MotherRequestDto {

    private Date lastMenstruationDate;

    private Date diagnoseDate;

    private String girlSurname;

    private PersonInfoRequestDto personInfo;
}
