package fr.hb.jg.centrale.controller_api;

import fr.hb.jg.centrale.dto.FuelDTO;
import fr.hb.jg.centrale.entity.Fuel;
import fr.hb.jg.centrale.json_view.JsonViews;
import fr.hb.jg.centrale.service.FuelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/fuel")
public class FuelRestController {

    private FuelService Service;

    @GetMapping
    @JsonView(JsonViews.FuelList.class)
    public List<Fuel> list() {
        return Service.list();
    }

    @PostMapping
    public Fuel create(@Valid @RequestBody FuelDTO dto) {
        return Service.create(dto);
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.FuelShow.class)
    public Fuel show(@PathVariable Long id) {
        return Service.findOneById(id);
    }

    @PutMapping("/{id}")
    public Fuel update(@Valid @RequestBody FuelDTO dto, @PathVariable Long id) {
        return Service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        Service.delete(id);
        return true;
    }

}