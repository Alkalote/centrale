package fr.hb.jg.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.jg.centrale.json_view.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @JsonView({JsonViews.ListingList.class, JsonViews.UserShowView.class})
    @Column(nullable = false,columnDefinition = "TEXT")
    private String path;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Listing listing;
}