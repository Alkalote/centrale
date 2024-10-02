package fr.hb.jg.centrale.repository;

import fr.hb.jg.centrale.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findOneByPhone(String phone);
    User findOneByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findUserByActivationCode(String code);



}