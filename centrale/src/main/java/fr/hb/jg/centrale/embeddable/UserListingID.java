package fr.hb.jg.centrale.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListingID implements Serializable {

    @Column(name = "listing_id")
    private String listingId;

    @Column(name = "user_id")
    private String userId;

}
