package com.vpr.app.dto.request;

import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for creating or updating an abnormality")
public class AbnormalityRequestDto {

    @Null(groups = OnCreate.class, message = "ID must not be provided when creating a new entity")
    @NotNull(groups = OnUpdate.class, message = "ID must be provided for update")
    @Positive(groups = OnUpdate.class, message = "ID must be a positive integer")
    @Schema(description = "Unique identifier of the abnormality (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
    private Long id;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 64, message = "Name must not exceed 64 characters")
    @Schema(description = "Name of the abnormality", example = "Hepatoblastoma", requiredMode = RequiredMode.REQUIRED)
    private String name;
}
