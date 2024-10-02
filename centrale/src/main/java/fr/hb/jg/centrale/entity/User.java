package fr.hb.jg.centrale.entity;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.jg.centrale.json_view.JsonViews;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView({JsonViews.ListingShow.class,JsonViews.UserShowView.class})
    private String uuid;


    @Column(nullable = false)
    @JsonView({JsonViews.ListingShow.class,JsonViews.UserShowView.class})
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonView(JsonViews.UserShowView.class)
    private String phone;

    @JsonView(JsonViews.UserShowView.class)
    private String siret;

    @JsonView(JsonViews.UserShowView.class)
    private String photo;

    private String activationCode;

    private LocalDateTime activationCodeSentAt;

    @JsonView(JsonViews.UserShowView.class)
    private LocalDateTime birthAt;

    @Column(nullable = false)
    @JsonView(JsonViews.UserShowView.class)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String roles;

    @JsonView(JsonViews.UserShowView.class)
    private String firstName;

    @JsonView(JsonViews.UserShowView.class)
    private String lastName;

    @OneToOne(mappedBy = "user")
    @JsonView(JsonViews.UserShowView.class)
    private Address address;

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserShowView.class)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Listing> listings= new ArrayList<>();

    @JsonView({JsonViews.ListingShow.class,JsonViews.UserShowView.class})
    public boolean isActive() {
        return activationCode == null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}