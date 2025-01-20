package com.vpr.app.dto.request;

import lombok.*;

import java.util.Date;

@Data
public class ProbandDRequestDto {

  private Date deathDate;

  private ProbandRequestDto proband;
}
