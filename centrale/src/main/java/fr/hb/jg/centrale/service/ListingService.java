package fr.hb.jg.centrale.service;

import fr.hb.jg.centrale.dto.ListingDTO;
import fr.hb.jg.centrale.entity.Listing;
import fr.hb.jg.centrale.exception.UpgradedEntityNotFoundException;
import fr.hb.jg.centrale.repository.ListingRepository;
import fr.hb.jg.centrale.service.interfaces.ServiceInterface;
import fr.hb.jg.centrale.service.interfaces.ServiceListInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@AllArgsConstructor
public class ListingService implements ServiceListInterface<Listing, String, ListingDTO, ListingDTO> {

    ListingRepository listingRepository;
    FuelService fuelService;
    UserService userService;
    AddressService addressService;
    ModelService modelService;

    @Override
    public Listing create(ListingDTO o) {
        Listing l = new Listing();
        l.setPrice(o.getPrice());
        l.setMileage(o.getMileage());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        l.setProducedAt(LocalDateTime.parse(o.getProducedAt(),formatter));
        l.setDescription(o.getDescription());
        l.setTitle(o.getTitle());
        l.setModel(modelService.findOneById(o.getModel()));
        l.setFuel(fuelService.findOneById(o.getFuel()));
        l.setOwner(userService.findOneById(o.getUser()));
        l.setAddress(addressService.findOneById(o.getAddress()));
        l.setCreatedAt(LocalDateTime.now());
        return listingRepository.saveAndFlush(l);

    }

    @Override
    public Listing update(ListingDTO o, String id) {
        Listing l = findOneById(id);
        l.setPrice(o.getPrice());
        l.setMileage(o.getMileage());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime t = LocalDate.parse(o.getProducedAt(), formatter).atStartOfDay();

        l.setProducedAt(t);
        l.setDescription(o.getDescription());
        l.setTitle(o.getTitle());
        l.setModel(modelService.findOneById(o.getModel()));
        l.setFuel(fuelService.findOneById(o.getFuel()));
        l.setOwner(userService.findOneById(o.getUser()));
        l.setAddress(addressService.findOneById(o.getAddress()));
        l.setCreatedAt(LocalDateTime.now());
        return listingRepository.saveAndFlush(l);
    }

    @Override
    public Boolean delete(String o) {
        listingRepository.deleteById(o);
        return true;
    }

    @Override
    public Listing findOneById(String id) {
        return listingRepository.findById(id).orElseThrow(UpgradedEntityNotFoundException::new);
    }

    @Override
    public List<Listing> list() {
        return listingRepository.findAll();
    }
}