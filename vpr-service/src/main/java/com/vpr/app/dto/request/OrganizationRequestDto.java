package com.vpr.app.dto.request;

import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Data
@Schema(description = "Data transfer object for creating or updating an organization")
public class OrganizationRequestDto {

    @Null(groups = OnCreate.class, message = "ID must not be provided when creating a new entity")
    @NotNull(groups = OnUpdate.class, message = "ID must be provided for update")
    @Positive(groups = OnUpdate.class, message = "ID must be a positive integer")
    @Schema(description = "Unique identifier of the organization (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
    private Long id;

    @Positive(message = "Organization number must be a positive integer")
    @Schema(description = "Unique number of the organization", example = "101", requiredMode = RequiredMode.REQUIRED)
    private int number;

    @NotBlank(message = "Organization name must not be blank")
    @Size(max = 64, message = "Organization name must not exceed 64 characters")
    @Schema(description = "Name of the organization", example = "Global Tech Solutions", requiredMode = RequiredMode.REQUIRED)
    private String name;
}
