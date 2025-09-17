package com.vpr.app.dto.request;

import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import com.vpr.app.enums.Gender;
import com.vpr.app.enums.LaborOutcome;
import com.vpr.app.enums.Ploidity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Data
@Schema(description = "Data transfer object for proband details")
public class ProbandRequestDto {

    @Null(groups = OnCreate.class, message = "ID must not be provided when creating a new entity")
    @NotNull(groups = OnUpdate.class, message = "ID must be provided for update")
    @Positive(groups = OnUpdate.class, message = "ID must be a positive integer")
    @Schema(description = "Unique identifier of proband details (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
    private Long id;

    @NotNull(message = "Birth date must not be null")
    @Past(message = "Birth date must be in the past")
    @Schema(description = "Date of birth of the proband", example = "2025-01-01", requiredMode = RequiredMode.REQUIRED)
    private LocalDate birthDate;

    @Size(max = 64, message = "Karyotype must not exceed 64 characters")
    @Schema(description = "Karyotype of the proband", example = "46,XY", requiredMode = RequiredMode.NOT_REQUIRED)
    private String karyotype;

    @Positive(message = "Pregnancy duration must be positive")
    @Schema(description = "Duration of pregnancy in weeks", example = "40", requiredMode = RequiredMode.REQUIRED)
    private int pregnancyDurationInWeeks;

    @Positive(message = "Weight must be positive")
    @Schema(description = "Birth weight of the proband in kilograms", example = "3.5", requiredMode = RequiredMode.REQUIRED)
    private double weight;

    @Positive(message = "Head circumference must be positive")
    @Schema(description = "Head circumference of the proband in centimeters", example = "34", requiredMode = RequiredMode.REQUIRED)
    private double head;

    @Positive(message = "Pregnancy number must be positive")
    @Schema(description = "Number of the pregnancy", example = "1", requiredMode = RequiredMode.REQUIRED)
    private int pregnancyNumber;

    @Schema(description = "Indicates whether the pregnancy was aborted", example = "false", requiredMode = RequiredMode.REQUIRED)
    private boolean isAborted;

    @NotBlank(message = "Gender must not be blank")
    @Schema(description = "Gender of the proband", example = "Male", requiredMode = RequiredMode.REQUIRED)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Ploidity must not be null")
    @Schema(description = "Ploidity details", requiredMode = RequiredMode.REQUIRED)
    @Enumerated(EnumType.STRING)
    private Ploidity ploid;

    @NotNull(message = "Labor outcome must not be null")
    @Schema(description = "Outcome of the labor", requiredMode = RequiredMode.REQUIRED)
    @Enumerated(EnumType.STRING)
    private LaborOutcome laborOutcome;

    @Schema(description = "Additional note about the proband", requiredMode = RequiredMode.NOT_REQUIRED)
    private NoteRequestDto note;

    @Schema(description = "Details of the father", requiredMode = RequiredMode.NOT_REQUIRED)
    private PersonInfoRequestDto father;

    @Schema(description = "Organization associated with the proband", requiredMode = RequiredMode.NOT_REQUIRED)
    private OrganizationRequestDto organization;

    @NotNull(message = "Mother details must not be null")
    @Schema(description = "Details of the mother", requiredMode = RequiredMode.REQUIRED)
    private MotherRequestDto mother;

    @NotNull(message = "Abnormality details must not be null")
    @Schema(description = "Abnormality associated with the proband", requiredMode = RequiredMode.REQUIRED)
    private AbnormalityRequestDto abnormality;
}

