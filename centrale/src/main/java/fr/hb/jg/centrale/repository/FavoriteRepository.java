package fr.hb.jg.centrale.repository;

import fr.hb.jg.centrale.embeddable.UserListingID;
import fr.hb.jg.centrale.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UserListingID> {



}