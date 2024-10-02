package fr.hb.jg.centrale.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {

    @NotBlank
    private String password;

    @NotBlank
    private String newPassword;

    private String phone;

    private String siret;

    private String photo;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private Long address;
    private String birthAt;
}
