package fr.hb.jg.centrale.controller_api;

import fr.hb.jg.centrale.dto.AddressDTO;
import fr.hb.jg.centrale.entity.Address;
import fr.hb.jg.centrale.json_view.JsonViews;
import fr.hb.jg.centrale.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/address")
public class AddressRestController {

    private AddressService Service;

    @GetMapping
    @JsonView(JsonViews.AddressShow.class)
    public List<Address> list() {
        return Service.list();
    }

    @PostMapping
    public Address create(@Valid @RequestBody AddressDTO dto) {
        return Service.create(dto);
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.AddressShow.class)
    public Address show(@PathVariable Long id) {
        return Service.findOneById(id);
    }

    @PutMapping("/{id}")
    public Address update(@Valid @RequestBody AddressDTO dto, @PathVariable Long id) {
        return Service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        Service.delete(id);
        return true;
    }

}