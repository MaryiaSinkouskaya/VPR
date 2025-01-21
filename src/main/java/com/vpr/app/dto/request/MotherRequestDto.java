package com.vpr.app.dto.request;

import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Data
@Schema(description = "Data transfer object for creating or updating a mother entity")
public class MotherRequestDto {

    @Null(groups = OnCreate.class, message = "ID must not be provided when creating a new entity")
    @NotNull(groups = OnUpdate.class, message = "ID must be provided for update")
    @Positive(groups = OnUpdate.class, message = "ID must be a positive integer")
    @Schema(description = "Unique identifier of the mother (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
    private Integer id;

    @NotNull(message = "Last menstruation date must not be null")
    @Schema(description = "The date of the last menstruation", example = "2025-01-01", requiredMode = RequiredMode.REQUIRED)
    private LocalDate lastMenstruationDate;

    @NotNull(message = "Diagnose date must not be null")
    @Schema(description = "The date when the diagnose was made", example = "2025-01-10", requiredMode = RequiredMode.REQUIRED)
    private LocalDate diagnoseDate;

    @NotBlank(message = "Girl's surname must not be blank")
    @Size(max = 64, message = "Girl's surname must not exceed 64 characters")
    @Schema(description = "Surname of the girl", example = "Doe", requiredMode = RequiredMode.REQUIRED)
    private String girlSurname;

    @NotNull(message = "Personal information must not be null")
    @Schema(description = "Personal information of the mother", requiredMode = RequiredMode.REQUIRED)
    private PersonInfoRequestDto personInfo;
}
