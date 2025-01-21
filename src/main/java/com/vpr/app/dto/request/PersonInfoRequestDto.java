package com.vpr.app.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;

@Data
@Schema(description = "Data transfer object for personal information")
public class PersonInfoRequestDto {

  @Positive(message = "ID must be a positive integer")
  @Schema(description = "Unique identifier of the personal information (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
  private Integer id;

  @NotBlank(message = "Name must not be blank")
  @Size(max = 64, message = "Name must not exceed 64 characters")
  @Schema(description = "First name of the person", example = "John", requiredMode = RequiredMode.REQUIRED)
  private String name;

  @NotBlank(message = "Surname must not be blank")
  @Size(max = 64, message = "Surname must not exceed 64 characters")
  @Schema(description = "Surname of the person", example = "Doe", requiredMode = RequiredMode.REQUIRED)
  private String surname;

  @Size(max = 64, message = "Patronymic must not exceed 64 characters")
  @Schema(description = "Patronymic of the person", example = "Edwardovich", requiredMode = RequiredMode.NOT_REQUIRED)
  private String patronymic;

  @Size(max = 15, message = "Phone number must not exceed 15 characters")
  @Schema(description = "Phone number of the person", example = "+123456789", requiredMode = RequiredMode.NOT_REQUIRED)
  private String phone;

  @NotNull(message = "Birth date must not be null")
  @Past(message = "Birth date must be in the past")
  @Schema(description = "Date of birth of the person", example = "1990-01-01", requiredMode = RequiredMode.REQUIRED)
  private Date birthDate;

  @NotNull(message = "Address must not be null")
  @Schema(description = "Address details of the person", requiredMode = RequiredMode.REQUIRED)
  private AddressRequestDto address;

  @Schema(description = "Workplace details of the person", requiredMode = RequiredMode.NOT_REQUIRED)
  private WorkplaceRequestDto workplace;
}
