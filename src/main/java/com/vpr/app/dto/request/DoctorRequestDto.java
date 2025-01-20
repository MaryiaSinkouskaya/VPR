package com.vpr.app.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for creating or updating a doctor")
public class DoctorRequestDto {

  @NotBlank(message = "Speciality must not be blank")
  @Size(max = 64, message = "Speciality must not exceed 64 characters")
  @Schema(description = "Speciality of the doctor", example = "Cardiologist", requiredMode = RequiredMode.REQUIRED)
  private String speciality;

  @Schema(description = "Personal information of the doctor", requiredMode = RequiredMode.REQUIRED)
  private PersonInfoRequestDto personInfo;
}
