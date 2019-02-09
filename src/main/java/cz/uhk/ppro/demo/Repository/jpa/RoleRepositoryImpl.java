package cz.uhk.ppro.demo.Repository.jpa;

import cz.uhk.ppro.demo.Model.Role;
import cz.uhk.ppro.demo.Repository.RoleRepository;
import org.springframework.stereotype.Repository;

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
