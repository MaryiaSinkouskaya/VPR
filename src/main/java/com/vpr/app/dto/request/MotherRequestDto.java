package com.vpr.app.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;

@Data
@Schema(description = "Data transfer object for creating or updating a mother entity")
public class MotherRequestDto {

  @NotNull(message = "Last menstruation date must not be null")
  @Schema(description = "The date of the last menstruation", example = "2025-01-01", requiredMode = RequiredMode.REQUIRED)
  private Date lastMenstruationDate;

  @NotNull(message = "Diagnose date must not be null")
  @Schema(description = "The date when the diagnose was made", example = "2025-01-10", requiredMode = RequiredMode.REQUIRED)
  private Date diagnoseDate;

  @NotBlank(message = "Girl's surname must not be blank")
  @Size(max = 64, message = "Girl's surname must not exceed 64 characters")
  @Schema(description = "Surname of the girl", example = "Doe", requiredMode = RequiredMode.REQUIRED)
  private String girlSurname;

  @NotNull(message = "Personal information must not be null")
  @Schema(description = "Personal information of the mother", requiredMode = RequiredMode.REQUIRED)
  private PersonInfoRequestDto personInfo;
}
