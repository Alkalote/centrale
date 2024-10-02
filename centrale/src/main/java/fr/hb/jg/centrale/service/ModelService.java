package fr.hb.jg.centrale.service;

import fr.hb.jg.centrale.dto.ModelDTO;
import fr.hb.jg.centrale.entity.Model;
import fr.hb.jg.centrale.exception.UpgradedEntityNotFoundException;
import fr.hb.jg.centrale.repository.BrandRepository;
import fr.hb.jg.centrale.repository.ModelRepository;
import fr.hb.jg.centrale.service.interfaces.ServiceInterface;
import fr.hb.jg.centrale.service.interfaces.ServiceListInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;


@Service
@AllArgsConstructor
public class ModelService implements ServiceListInterface<Model, Long, ModelDTO, ModelDTO> {

    private ModelRepository modelRepository;
    private BrandService brandService;

    @Override
    public Model create(ModelDTO o) {
        Model m = new Model();
        m.setName(o.getName());
        m.setBrand(brandService.findOneById(o.getBrand()));
        return modelRepository.saveAndFlush(m);

    }

    @Override
    public Model update(ModelDTO o, Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long o) {
        Model m = modelRepository.findById(o).orElseThrow(UpgradedEntityNotFoundException::new);
        m.setName("Modèle supprimé");
        modelRepository.saveAndFlush(m);
        return true;
    }

    @Override
    public Model findOneById(Long id) {
        return null;
    }

    @Override
    public List<Model> list() {
        return modelRepository.findAll();
    }
}