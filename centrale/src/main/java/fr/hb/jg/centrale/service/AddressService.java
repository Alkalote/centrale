package fr.hb.jg.centrale.service;

import fr.hb.jg.centrale.dto.AddressDTO;
import fr.hb.jg.centrale.entity.Address;
import fr.hb.jg.centrale.exception.UpgradedEntityNotFoundException;
import fr.hb.jg.centrale.repository.AddressRepository;
import fr.hb.jg.centrale.service.interfaces.ServiceInterface;
import fr.hb.jg.centrale.service.interfaces.ServiceListInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;


@Service
@AllArgsConstructor
public class AddressService implements ServiceListInterface<Address, Long, AddressDTO, AddressDTO> {

    private AddressRepository addressRepository;
    private UserService userService;

    @Override
    public Address create(AddressDTO o) {
        Address a = new Address();
        a.setCity(o.getCity());
        a.setStreetName(o.getStreetName());
        a.setStreetNumber(o.getStreetNumber());
        a.setZipCode(o.getZipCode());
        a.setLongitude(o.getLongitude());
        a.setLatitude(o.getLatitude());

        if(!o.getUser().isBlank()){
            a.setUser(userService.findOneById(o.getUser()));
        }
        return addressRepository.saveAndFlush(a);
    }

    @Override
    public Address update(AddressDTO o, Long id) {
        Address a = findOneById(id);
        a.setCity(o.getCity());
        a.setStreetName(o.getStreetName());
        a.setStreetNumber(o.getStreetNumber());
        a.setZipCode(o.getZipCode());
        a.setLongitude(o.getLongitude());
        a.setLatitude(o.getLatitude());

        if(!o.getUser().isBlank()){
            a.setUser(userService.findOneById(o.getUser()));
        }
        return addressRepository.saveAndFlush(a);
    }

    @Override
    public Boolean delete(Long o) {
        addressRepository.deleteById(o);
        return true;
    }

    @Override
    public Address findOneById(Long id) {
        return addressRepository.findById(id).orElseThrow(UpgradedEntityNotFoundException::new);
    }

    @Override
    public List<Address> list() {
        return addressRepository.findAll();
    }
}