package fr.hb.jg.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.jg.centrale.embeddable.UserListingID;
import fr.hb.jg.centrale.json_view.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Favorite {

    @EmbeddedId
    private UserListingID id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "listing_id", insertable = false, updatable = false)
    @JsonView(JsonViews.UserShowView.class)
    private Listing listing;

}