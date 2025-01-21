package com.vpr.app.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for creating or updating an organization")
public class OrganizationRequestDto {

  @Positive(message = "ID must be a positive integer")
  @Schema(description = "Unique identifier of the organization (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
  private Integer id;

  @Positive(message = "Organization number must be a positive integer")
  @Schema(description = "Unique number of the organization", example = "101", requiredMode = RequiredMode.REQUIRED)
  private int number;

  @NotBlank(message = "Organization name must not be blank")
  @Size(max = 64, message = "Organization name must not exceed 64 characters")
  @Schema(description = "Name of the organization", example = "Global Tech Solutions", requiredMode = RequiredMode.REQUIRED)
  private String name;
}
