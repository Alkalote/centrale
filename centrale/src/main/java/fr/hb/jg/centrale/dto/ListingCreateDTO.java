package fr.hb.jg.centrale.dto;

import fr.hb.jg.centrale.entity.Model;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ListingDTO {

    private Long price;

    private Long mileage;

    private String producedAt;

    private String description;

    private String title;

    private Long model;

    private String user;

    private Long address;

    private Long fuel;


}
