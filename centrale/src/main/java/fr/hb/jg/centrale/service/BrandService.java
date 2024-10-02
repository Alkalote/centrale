package fr.hb.jg.centrale.service;

import fr.hb.jg.centrale.dto.BrandDTO;
import fr.hb.jg.centrale.entity.Brand;
import fr.hb.jg.centrale.exception.UpgradedEntityNotFoundException;
import fr.hb.jg.centrale.repository.BrandRepository;
import fr.hb.jg.centrale.service.interfaces.ServiceInterface;
import fr.hb.jg.centrale.service.interfaces.ServiceListInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;


@Service
@AllArgsConstructor
public class BrandService implements ServiceListInterface<Brand, Long, BrandDTO, BrandDTO> {

    private BrandRepository brandRepository;

    @Override
    public Brand create(BrandDTO o) {
        Brand b = new Brand();
        b.setName(o.getName());
        return brandRepository.saveAndFlush(b);
    }

    @Override
    public Brand update(BrandDTO o, Long id) {
        Brand b = findOneById(id);
        b.setName(o.getName());
        return brandRepository.saveAndFlush(b);
    }

    @Override
    public Boolean delete(Long o) {
        Brand b = brandRepository.findById(o).orElseThrow(UpgradedEntityNotFoundException::new);
        b.setName("Marque supprimée");
        brandRepository.saveAndFlush(b);
        return true;
    }

    @Override
    public Brand findOneById(Long id) {
        return brandRepository.findById(id).orElseThrow(UpgradedEntityNotFoundException::new);
    }

    @Override
    public List<Brand> list() {
        return brandRepository.findAll();
    }
}