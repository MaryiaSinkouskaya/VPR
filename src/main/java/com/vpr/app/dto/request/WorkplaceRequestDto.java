package com.vpr.app.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for workplace details")
public class WorkplaceRequestDto {

  @Positive(message = "ID must be a positive integer")
  @Schema(description = "Unique identifier of workplace details (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
  private Integer id;

  @NotBlank(message = "Job type must not be blank")
  @Size(max = 64, message = "Job type must not exceed 64 characters")
  @Schema(description = "Type of job", example = "Software Engineer", requiredMode = RequiredMode.REQUIRED)
  private String jobType;

  @NotBlank(message = "Company name must not be blank")
  @Size(max = 64, message = "Company name must not exceed 64 characters")
  @Schema(description = "Name of the company", example = "Tech Corp", requiredMode = RequiredMode.REQUIRED)
  private String company;
}
