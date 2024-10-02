package fr.hb.jg.centrale.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModelDTO {

    @NotBlank
    private String name;

    private Long Brand;
}
