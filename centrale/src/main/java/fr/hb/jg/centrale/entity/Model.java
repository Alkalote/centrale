package fr.hb.jg.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.jg.centrale.json_view.JsonViews;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.ModelList.class)
    private Long id;

    @NotBlank
    @JsonView({JsonViews.ListingShow.class,JsonViews.ModelList.class,JsonViews.BrandShow.class})
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.ModelList.class})
    private Brand brand;

    @OneToMany(mappedBy = "model")
    private List<Listing> listings = new ArrayList<>();

}