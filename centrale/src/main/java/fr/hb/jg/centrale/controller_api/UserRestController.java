package fr.hb.jg.centrale.controller_api;

import fr.hb.jg.centrale.dto.UserCreateDTO;
import fr.hb.jg.centrale.dto.UserUpdateDTO;
import fr.hb.jg.centrale.entity.User;
import fr.hb.jg.centrale.json_view.JsonViews;
import fr.hb.jg.centrale.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private UserService Service;

    @GetMapping
    @JsonView(JsonViews.UserMinimalView.class)
    public List<User> list() {
        return Service.list();
    }




    @GetMapping("/{uuid}")
    @JsonView(JsonViews.UserShowView.class)
    public User show(@PathVariable String uuid) {
        return Service.findOneById(uuid);
    }

    @GetMapping("/activate/{code}")
    @JsonView(JsonViews.UserShowView.class)
    public User activate(@PathVariable String code) {
        return Service.activate(code);
    }

    @PutMapping("/{uuid}")
    public User update(@Valid @RequestBody UserUpdateDTO dto, @PathVariable String uuid) {
        return Service.update(dto, uuid);
    }

    @DeleteMapping("/{uuid}")
    public boolean delete(@PathVariable String uuid) {
        Service.delete(uuid);
        return true;
    }

}