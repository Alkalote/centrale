package fr.hb.jg.centrale.controller_api;

import fr.hb.jg.centrale.dto.ListingDTO;
import fr.hb.jg.centrale.entity.Listing;
import fr.hb.jg.centrale.json_view.JsonViews;
import fr.hb.jg.centrale.service.ListingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/listing")
public class ListingRestController {

    private ListingService Service;

    @GetMapping
    @JsonView(JsonViews.ListingList.class)
    public List<Listing> list() {
        return Service.list();
    }

    @PostMapping
    public Listing create(@Valid @RequestBody ListingDTO dto) {
        return Service.create(dto);
    }

    @GetMapping("/{id}")
    //@JsonView(JsonViews.ListingShow.class)
    public Listing show(@PathVariable String id) {
        return Service.findOneById(id);
    }

    @PutMapping("/{id}")
    public Listing update(@Valid @RequestBody ListingDTO dto, @PathVariable String id) {
        return Service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        Service.delete(id);
        return true;
    }

}