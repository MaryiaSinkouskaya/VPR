package com.vpr.app.dto.request;

import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Data
@Schema(description = "Data transfer object for creating or updating a note")
public class NoteRequestDto {

    @Null(groups = OnCreate.class, message = "ID must not be provided when creating a new entity")
    @NotNull(groups = OnUpdate.class, message = "ID must be provided for update")
    @Positive(groups = OnUpdate.class, message = "ID must be a positive integer")
    @Schema(description = "Unique identifier of the note (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
    private Long id;

    @NotNull(message = "Date must not be null")
    @Schema(description = "The date when the note was created", example = "2025-01-01", requiredMode = RequiredMode.REQUIRED)
    private LocalDate date;

    @NotBlank(message = "Note content must not be blank")
    @Schema(description = "The content of the note", example = "This is an important note.", requiredMode = RequiredMode.REQUIRED)
    private String note;
}
