package fr.hb.jg.centrale.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.jg.centrale.custom_response.JwtResponse;
import fr.hb.jg.centrale.dto.UserLoginDTO;
import fr.hb.jg.centrale.dto.UserRegisterDTO;
import fr.hb.jg.centrale.entity.User;
import fr.hb.jg.centrale.json_view.JsonViews;
import fr.hb.jg.centrale.security.JwtAuthenticatorService;
import fr.hb.jg.centrale.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SecurityRestController {

    private final UserService userService;

    private final JwtAuthenticatorService jwtAuthenticatorService;

    @PostMapping("/api/register")
    public User register(@Valid @RequestBody UserRegisterDTO user) {
        return userService.create(user);
    }

    @PostMapping("/api/login")

    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginDTO user) {
        return jwtAuthenticatorService.authenticate(user);
    }

}