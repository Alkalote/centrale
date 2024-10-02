package fr.hb.jg.centrale.controller_api;

import fr.hb.jg.centrale.dto.ImageDTO;
import fr.hb.jg.centrale.entity.Image;
import fr.hb.jg.centrale.service.ImageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/image")
public class ImageRestController {

    private ImageService Service;

    @GetMapping
    //@JsonView(JsonViews.ImageMinimalView.class)
    public List<Image> list() {
        return Service.list();
    }

    @PostMapping
    public Image create(@Valid @RequestBody ImageDTO dto) {
        return Service.create(dto);
    }

    @GetMapping("/{id}")
    //@JsonView(JsonViews.ImageShow.class)
    public Image show(@PathVariable Long id) {
        return Service.findOneById(id);
    }

    @PutMapping("/{id}")
    public Image update(@Valid @RequestBody ImageDTO dto, @PathVariable Long id) {
        return Service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        Service.delete(id);
        return true;
    }

}