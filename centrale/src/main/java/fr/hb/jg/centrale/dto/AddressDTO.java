package fr.hb.jg.centrale.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    @NotBlank
    private String streetNumber;

    @NotBlank
    private String streetName;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;

    private String user;

}
