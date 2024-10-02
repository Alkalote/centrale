package fr.hb.jg.centrale.repository;

import fr.hb.jg.centrale.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {


}