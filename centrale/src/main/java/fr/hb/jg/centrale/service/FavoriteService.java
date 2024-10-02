package fr.hb.jg.centrale.service;

import fr.hb.jg.centrale.embeddable.UserListingID;
import fr.hb.jg.centrale.entity.Favorite;
import fr.hb.jg.centrale.repository.FavoriteRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class FavoriteService {

    private FavoriteRepository favoriteRepository;

    public Boolean handleFavorite(UserListingID o) {
        System.out.println(o);
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(o);
        if (optionalFavorite.isEmpty()) {
            Favorite favorite = new Favorite();
            favorite.setId(o);
            favorite.setCreatedAt(LocalDateTime.now());
            favoriteRepository.saveAndFlush(favorite);
            return true;
        }
        favoriteRepository.delete(optionalFavorite.get());
        return false;
    }
}