package com.vpr.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AbnormalityRequestDto {

    @NotBlank(message = "Name must not be blank")
    @Size(max = 64, message = "Name must not exceed 64 characters")
    private String name;
}
