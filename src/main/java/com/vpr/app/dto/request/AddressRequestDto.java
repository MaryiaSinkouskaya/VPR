package com.vpr.app.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for creating or updating an address")
public class AddressRequestDto {

  @NotBlank(message = "Street must not be blank")
  @Size(max = 64, message = "Street must not exceed 64 characters")
  @Schema(description = "Name of the street", example = "Main Street", requiredMode = RequiredMode.REQUIRED)
  private String street;

  @NotNull(message = "Building number must be provided")
  @Positive(message = "Building number must be a positive integer")
  @Schema(description = "Building number on the street", example = "123", requiredMode = RequiredMode.REQUIRED)
  private Integer building;

  @NotNull(message = "Apartment number must be provided")
  @Positive(message = "Apartment number must be a positive integer")
  @Schema(description = "Apartment number in the building", example = "45", requiredMode = RequiredMode.REQUIRED)
  private Integer apartment;

  @NotBlank(message = "Town must not be blank")
  @Size(max = 64, message = "Town must not exceed 64 characters")
  @Schema(description = "Name of the town or city", example = "Springfield", requiredMode = RequiredMode.REQUIRED)
  private String town;
}