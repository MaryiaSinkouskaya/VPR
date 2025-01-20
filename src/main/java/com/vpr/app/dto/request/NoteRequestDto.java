package com.vpr.app.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class NoteRequestDto {

  private Date date;

  private String note;
}
