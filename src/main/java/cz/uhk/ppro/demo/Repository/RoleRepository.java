package cz.uhk.ppro.demo.Repository;

import cz.uhk.ppro.demo.Model.Role;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findByName(String name);
}
