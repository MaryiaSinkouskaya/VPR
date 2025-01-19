package com.vpr.app.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vpr.app.entity.Proband;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
public class ProbandDRequestDto {

  private Date deathDate;

  private ProbandRequestDto proband;
}
