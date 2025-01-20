package com.vpr.app.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import java.util.Date;

@Data
@Schema(description = "Data transfer object for proband death details")
public class ProbandDRequestDto {

  @PastOrPresent(message = "Death date must not be in the future")
  @Schema(description = "Date of death of the proband", example = "2025-01-01", requiredMode = RequiredMode.NOT_REQUIRED)
  private Date deathDate;

  @NotNull(message = "Proband details must not be null")
  @Schema(description = "Details of the associated proband", requiredMode = RequiredMode.REQUIRED)
  private ProbandRequestDto proband;
}