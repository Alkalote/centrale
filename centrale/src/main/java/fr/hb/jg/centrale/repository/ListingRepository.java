package fr.hb.jg.centrale.repository;

import fr.hb.jg.centrale.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

    Listing findOneByPrice(Long price);

    Listing findOneByMileage(Long mileage);

}