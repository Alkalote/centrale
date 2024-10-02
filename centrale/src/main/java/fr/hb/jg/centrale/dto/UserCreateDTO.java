package fr.hb.jg.centrale.dto;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.jg.centrale.json_view.JsonViews;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateDTO {

    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String password;
    @NotBlank
    private String password2;

    @NotBlank
    private String phone;

    private String siret;

    private String photo;

    private Long address;
    @NotBlank
    private String birthAt;




}
