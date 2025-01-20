package com.vpr.app.dto.request;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Data
@Schema(description = "Data transfer object for creating or updating a note")
public class NoteRequestDto {

  @NotNull(message = "Date must not be null")
  @Schema(description = "The date when the note was created", example = "2025-01-01", requiredMode = RequiredMode.REQUIRED)
  private Date date;

  @NotBlank(message = "Note content must not be blank")
  @Schema(description = "The content of the note", example = "This is an important note.", requiredMode = RequiredMode.REQUIRED)
  private String note;
}
