package fr.hb.jg.centrale.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {

    @NotBlank
    private String password;


    @NotBlank
    private String email;

}
