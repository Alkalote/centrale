package fr.hb.jg.centrale.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageDTO {

    @NotBlank
    private String path;

    private String listing;
}
