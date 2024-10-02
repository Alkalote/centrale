package fr.hb.jg.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.jg.centrale.json_view.JsonViews;
import jakarta.persistence.*;
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
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView({JsonViews.ListingList.class, JsonViews.UserShowView.class})
    private String uuid;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingList.class, JsonViews.UserShowView.class})
    private Long price;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingList.class, JsonViews.UserShowView.class})
    private Long mileage;

    @JsonView({JsonViews.ListingList.class, JsonViews.UserShowView.class})
    @Column(nullable = false)
    private LocalDateTime producedAt;

    @Column(nullable = false)
    @JsonView(JsonViews.ListingShow.class)
    private LocalDateTime createdAt;

    @Column(nullable = false,columnDefinition = "TEXT")
    @JsonView(JsonViews.ListingShow.class)
    private String description;

    @Column(nullable = false)
    @JsonView({JsonViews.ListingList.class, JsonViews.UserShowView.class})
    private String title;

    @ManyToOne
    @JsonView(JsonViews.ListingShow.class)
    @JoinColumn(nullable = false)
    private Model model;

    @OneToMany(mappedBy = "listing")
    @JsonView({JsonViews.ListingList.class, JsonViews.UserShowView.class})
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.ListingShow.class)
    private Fuel fuel;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.ListingShow.class)
    private Address address ;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.ListingShow.class)
    private User owner;


}