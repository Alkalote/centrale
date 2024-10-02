package fr.hb.jg.centrale.repository;

import fr.hb.jg.centrale.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {


}