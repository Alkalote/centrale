package fr.hb.jg.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.jg.centrale.json_view.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.FuelList.class)
    private Long id;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.FuelList.class})
    private String type;

    @Column(nullable = false)
    @JsonView(JsonViews.FuelList.class)
    private String logo;

    @OneToMany(mappedBy = "fuel")
    private List<Listing> listings = new ArrayList<>();

}