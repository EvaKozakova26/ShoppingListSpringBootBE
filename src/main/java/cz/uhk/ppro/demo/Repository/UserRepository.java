package cz.uhk.ppro.demo.Repository;

import cz.uhk.ppro.demo.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(int id);

    Optional<User> findByUsername(String username);

    void save(User user);
}
