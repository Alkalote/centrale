package fr.hb.jg.centrale.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FuelDTO {

    @NotBlank
    private String type;

    @NotBlank
    private String logo;
}
