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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.AddressShow.class)
    private Long id;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.AddressShow.class})
    private String streetNumber;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.AddressShow.class})
    private String streetName;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.AddressShow.class})
    private String zipCode;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.AddressShow.class})
    private String city;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.AddressShow.class})
    private String latitude;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.AddressShow.class})
    private String longitude;

    @OneToMany(mappedBy = "address")
    private List<Listing> listings = new ArrayList<>();

    @OneToOne
    private User user;


}