package com.vpr.app.dto.request;

import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Data
@Schema(description = "Data transfer object for proband death details")
public class ProbandDRequestDto {

    @Null(groups = OnCreate.class, message = "ID must not be provided when creating a new entity")
    @NotNull(groups = OnUpdate.class, message = "ID must be provided for update")
    @Positive(groups = OnUpdate.class, message = "ID must be a positive integer")
    @Schema(description = "Unique identifier of proband death details (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
    private Integer id;

    @PastOrPresent(message = "Death date must not be in the future")
    @Schema(description = "Date of death of the proband", example = "2025-01-01", requiredMode = RequiredMode.NOT_REQUIRED)
    private LocalDate deathDate;

    @NotNull(message = "Proband details must not be null")
    @Schema(description = "Details of the associated proband", requiredMode = RequiredMode.REQUIRED)
    private ProbandRequestDto proband;
}