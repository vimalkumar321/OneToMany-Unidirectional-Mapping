package com.vimal.demo.onetomanyunidirectional.repository;

import com.vimal.demo.onetomanyunidirectional.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
