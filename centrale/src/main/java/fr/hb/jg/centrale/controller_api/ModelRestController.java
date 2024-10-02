package fr.hb.jg.centrale.controller_api;

import fr.hb.jg.centrale.dto.ModelDTO;
import fr.hb.jg.centrale.entity.Model;
import fr.hb.jg.centrale.json_view.JsonViews;
import fr.hb.jg.centrale.service.ModelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/model")
public class ModelRestController {

    private ModelService Service;

    @GetMapping
    @JsonView(JsonViews.ModelList.class)
    public List<Model> list() {
        return Service.list();
    }

    @PostMapping
    public Model create(@Valid @RequestBody ModelDTO dto) {
        return Service.create(dto);
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.ModelShow.class)
    public Model show(@PathVariable Long id) {
        return Service.findOneById(id);
    }

    @PutMapping("/{id}")
    public Model update(@Valid @RequestBody ModelDTO dto, @PathVariable Long id) {
        return Service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        Service.delete(id);
        return true;
    }

}