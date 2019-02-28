package shopping_list.repository;

import shopping_list.model.Role;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findByName(String name);
}
