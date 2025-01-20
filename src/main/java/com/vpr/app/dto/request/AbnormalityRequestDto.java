package com.vpr.app.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for creating or updating an abnormality")
public class AbnormalityRequestDto {

  @NotBlank(message = "Name must not be blank")
  @Size(max = 64, message = "Name must not exceed 64 characters")
  @Schema(description = "Name of the abnormality", example = "Hepatoblastoma", requiredMode = RequiredMode.REQUIRED)
  private String name;
}
