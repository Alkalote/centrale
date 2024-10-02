package fr.hb.jg.centrale.controller_api;

import fr.hb.jg.centrale.dto.BrandDTO;
import fr.hb.jg.centrale.entity.Brand;
import fr.hb.jg.centrale.json_view.JsonViews;
import fr.hb.jg.centrale.service.BrandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/")
public class BrandRestController {

    private BrandService Service;

    @GetMapping
    @JsonView(JsonViews.BrandList.class)
    public List<Brand> list() {
        return Service.list();
    }

    @PostMapping
    public Brand create(@Valid @RequestBody BrandDTO dto) {
        return Service.create(dto);
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.BrandShow.class)
    public Brand show(@PathVariable Long id) {
        return Service.findOneById(id);
    }

    @PutMapping("/{id}")
    public Brand update(@Valid @RequestBody BrandDTO dto, @PathVariable Long id) {
        return Service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        Service.delete(id);
        return true;
    }

}