package shopping_list.repository.jpa;

import org.springframework.stereotype.Repository;
import shopping_list.model.Role;
import shopping_list.repository.RoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Role> findByName(String name) {
        Query query = this.em.createQuery("SELECT r FROM Role r WHERE r.name =:name");
        query.setParameter("name", name);
        return (Optional<Role>) query.setMaxResults(1).getResultList().stream().findFirst();
    }
}
