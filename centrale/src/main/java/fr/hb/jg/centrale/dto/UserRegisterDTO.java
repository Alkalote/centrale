package fr.hb.jg.centrale.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDTO {

    @NotBlank
    private String password;

    @NotBlank
    private String password2;


    @NotBlank
    private String email;

    @NotBlank
    private String birthAt;

}
