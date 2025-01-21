package com.vpr.app.dto.request;

import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Data
@Schema(description = "Data transfer object for workplace details")
public class WorkplaceRequestDto {

    @Null(groups = OnCreate.class, message = "ID must not be provided when creating a new entity")
    @NotNull(groups = OnUpdate.class, message = "ID must be provided for update")
    @Positive(groups = OnUpdate.class, message = "ID must be a positive integer")
    @Schema(description = "Unique identifier of workplace details (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
    private Integer id;

    @NotBlank(message = "Job type must not be blank")
    @Size(max = 64, message = "Job type must not exceed 64 characters")
    @Schema(description = "Type of job", example = "Software Engineer", requiredMode = RequiredMode.REQUIRED)
    private String jobType;

    @NotBlank(message = "Company name must not be blank")
    @Size(max = 64, message = "Company name must not exceed 64 characters")
    @Schema(description = "Name of the company", example = "Tech Corp", requiredMode = RequiredMode.REQUIRED)
    private String company;
}
