package fr.hb.jg.centrale.service;

import fr.hb.jg.centrale.dto.ImageDTO;
import fr.hb.jg.centrale.entity.Image;
import fr.hb.jg.centrale.exception.UpgradedEntityNotFoundException;
import fr.hb.jg.centrale.repository.ImageRepository;
import fr.hb.jg.centrale.service.interfaces.ServiceInterface;
import fr.hb.jg.centrale.service.interfaces.ServiceListInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;


@Service
@AllArgsConstructor
public class ImageService implements ServiceListInterface<Image, Long, ImageDTO, ImageDTO> {

    private ImageRepository imageRepository;

    private ListingService listingService;

    @Override
    public Image create(ImageDTO o) {
        Image a = new Image();
        a.setPath(o.getPath());
        a.setListing(listingService.findOneById(o.getListing()));
        return imageRepository.saveAndFlush(a);
    }

    @Override
    public Image update(ImageDTO o, Long id) {
        Image a = findOneById(id);
        a.setPath(o.getPath());
        a.setListing(listingService.findOneById(o.getListing()));
        return imageRepository.saveAndFlush(a);
    }

    @Override
    public Boolean delete(Long o) {
        imageRepository.deleteById(o);
        return true;
    }

    @Override
    public Image findOneById(Long id) {
        return imageRepository.findById(id).orElseThrow(UpgradedEntityNotFoundException::new);
    }

    @Override
    public List<Image> list() {
        return imageRepository.findAll();
    }
}