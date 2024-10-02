package fr.hb.jg.centrale.service;

import fr.hb.jg.centrale.dto.UserCreateDTO;
import fr.hb.jg.centrale.dto.UserRegisterDTO;
import fr.hb.jg.centrale.dto.UserUpdateDTO;
import fr.hb.jg.centrale.entity.Address;
import fr.hb.jg.centrale.entity.User;
import fr.hb.jg.centrale.exception.AlreadyActiveException;
import fr.hb.jg.centrale.exception.ExpiredCodeException;
import fr.hb.jg.centrale.exception.UpgradedEntityNotFoundException;
import fr.hb.jg.centrale.repository.AddressRepository;
import fr.hb.jg.centrale.repository.UserRepository;
import fr.hb.jg.centrale.service.interfaces.ServiceListInterface;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@AllArgsConstructor
public class UserService implements ServiceListInterface<User, String, UserRegisterDTO, UserUpdateDTO>, UserDetailsService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User create(UserRegisterDTO o) {

        User u = new User();
        u.setEmail(o.getEmail());
        u.setPassword(passwordEncoder.encode(o.getPassword()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime t = LocalDate.parse(o.getBirthAt(), formatter).atStartOfDay();
        u.setBirthAt(t);

        u.setRoles("[\"ROLE_USER\"]");
;
        u.setActivationCode(UUID.randomUUID().toString());
        u.setCreatedAt(LocalDateTime.now());

        return userRepository.saveAndFlush(u);
    }

    @Override
    public User update(UserUpdateDTO o, String id) {
        User user = findOneById(id);
        user.setPhoto(o.getPhoto());
        user.setSiret(o.getSiret());
        user.setPhone(o.getPhone());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime t = LocalDate.parse(o.getBirthAt(), formatter).atStartOfDay();
        user.setBirthAt(t);
        user.setFirstName(o.getFirstName());
        user.setLastName(o.getLastName());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Boolean delete(String uuid) {
        try {
            User user = findOneById(uuid);
            user.setPhone(null);
            user.setBirthAt(null);
            user.setPhoto(null);
            user.setSiret(null);
            user.setLastName(null);
            user.setFirstName(null);
            user.setEmail("Utilisateur supprimé");
            Address address = user.getAddress();
            if (address!= null) {
                address.setUser(null);
                addressRepository.save(address);
            }
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User findOneById(String id) {
        return userRepository.findById(id).orElseThrow(UpgradedEntityNotFoundException::new);
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    public User activate(String code) {
        User user = userRepository.findUserByActivationCode(code)
                .orElseThrow(() -> new AlreadyActiveException("Ce code d'activation n'existe pas !"));

        LocalDateTime current = LocalDateTime.now();
        if (current.isAfter(user.getActivationCodeSentAt().plusMinutes(15))) {
            throw new ExpiredCodeException("La durée du code a expiré");
        }
        user.setActivationCode(null);
        user.setActivationCodeSentAt(null);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("L'éléphant est dans le magasin porcelaine"));

        return new org.springframework.security.core.userdetails.User(u.getEmail(),u.getPassword(),userGrantedAuthorities(u.getRoles()));
    }

    private List<GrantedAuthority> userGrantedAuthorities(String jsonRoles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // jsonRoles = ["ROLE_USER"] ou ["ROLE_USER", "ROLE_ADMIN"]
        List<String> roles = Collections.singletonList(jsonRoles);
        roles.forEach(r -> {
            // r => "ROLE_USER" puis "ROLE_ADMIN"
            authorities.add(new SimpleGrantedAuthority(r));
        });
        return authorities;
    }
}