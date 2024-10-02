package fr.hb.jg.centrale.controller_api;

import fr.hb.jg.centrale.embeddable.UserListingID;
import fr.hb.jg.centrale.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/favorite")
public class FavoriteRestController {

    private FavoriteService favoriteService;

    @PostMapping
    public Boolean handleFavorite(@RequestBody UserListingID data) {
        return favoriteService.handleFavorite(data);
    }

}