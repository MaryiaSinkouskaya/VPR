package com.vpr.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressRequestDto {

    @NotBlank(message = "Street must not be blank")
    @Size(max = 64, message = "Street must not exceed 64 characters")
    private String street;

    @NotNull(message = "Building number must be provided")
    @Positive(message = "Building number must be a positive integer")
    private Integer building;

    @NotNull(message = "Apartment number must be provided")
    @Positive(message = "Apartment number must be a positive integer")
    private Integer apartment;

    @NotBlank(message = "Town must not be blank")
    @Size(max = 64, message = "Town must not exceed 64 characters")
    private String town;
}