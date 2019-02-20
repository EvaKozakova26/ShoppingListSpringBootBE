package shopping_list.Repository;

import shopping_list.Model.Role;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findByName(String name);
}
