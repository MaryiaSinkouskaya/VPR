package com.vpr.app.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vpr.app.entity.Proband;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class NoteRequestDto {

  private Date date;

  private String note;
}
