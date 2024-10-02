package fr.hb.jg.centrale.service;

import fr.hb.jg.centrale.dto.FuelDTO;
import fr.hb.jg.centrale.entity.Fuel;
import fr.hb.jg.centrale.exception.UpgradedEntityNotFoundException;
import fr.hb.jg.centrale.repository.FuelRepository;
import fr.hb.jg.centrale.service.interfaces.ServiceInterface;
import fr.hb.jg.centrale.service.interfaces.ServiceListInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;


@Service
@AllArgsConstructor
public class FuelService implements ServiceListInterface<Fuel, Long, FuelDTO, FuelDTO> {

    private FuelRepository fuelRepository;

    @Override
    public Fuel create(FuelDTO o) {
        Fuel f = new Fuel();
        f.setLogo(o.getLogo());
        f.setType(o.getType());
        return fuelRepository.saveAndFlush(f);
    }

    @Override
    public Fuel update(FuelDTO o, Long id) {
        Fuel f = findOneById(id);
        f.setLogo(o.getLogo());
        f.setType(o.getType());
        return fuelRepository.saveAndFlush(f);
    }

    @Override
    public Boolean delete(Long o) {

        Fuel f = findOneById(o);
        f.setType("Carburant supprimé");
        fuelRepository.saveAndFlush(f);

        return true;
    }

    @Override
    public Fuel findOneById(Long id) {
        return fuelRepository.findById(id).orElseThrow(UpgradedEntityNotFoundException::new);
    }

    @Override
    public List<Fuel> list() {
        return fuelRepository.findAll();
    }
}