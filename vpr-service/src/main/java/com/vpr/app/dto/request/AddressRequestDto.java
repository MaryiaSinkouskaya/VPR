package com.vpr.app.dto.request;

import com.vpr.app.dto.request.validation.markers.OnCreate;
import com.vpr.app.dto.request.validation.markers.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for creating or updating an address")
public class AddressRequestDto {

    @Null(groups = OnCreate.class, message = "ID must not be provided when creating a new entity")
    @NotNull(groups = OnUpdate.class, message = "ID must be provided for update")
    @Positive(groups = OnUpdate.class, message = "ID must be a positive integer")
    @Schema(description = "Unique identifier of the address (used for updates)", example = "10", requiredMode = RequiredMode.NOT_REQUIRED)
    private Long id;

    @NotBlank(message = "Street must not be blank")
    @Size(max = 64, message = "Street must not exceed 64 characters")
    @Schema(description = "Name of the street", example = "Main Street", requiredMode = RequiredMode.REQUIRED)
    private String street;

    @NotNull(message = "Building number must be provided")
    @Positive(message = "Building number must be a positive integer")
    @Schema(description = "Building number on the street", example = "123", requiredMode = RequiredMode.REQUIRED)
    private Integer building;

    @NotNull(message = "Apartment number must be provided")
    @Positive(message = "Apartment number must be a positive integer")
    @Schema(description = "Apartment number in the building", example = "45", requiredMode = RequiredMode.REQUIRED)
    private Integer apartment;

    @NotBlank(message = "Town must not be blank")
    @Size(max = 64, message = "Town must not exceed 64 characters")
    @Schema(description = "Name of the town or city", example = "Springfield", requiredMode = RequiredMode.REQUIRED)
    private String town;
}