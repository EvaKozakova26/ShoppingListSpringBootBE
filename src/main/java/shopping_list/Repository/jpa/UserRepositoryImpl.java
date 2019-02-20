package shopping_list.Repository.jpa;

import org.springframework.stereotype.Repository;
import shopping_list.Model.User;
import shopping_list.Repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return this.em.createQuery("SELECT users FROM User users").getResultList();

    }

    @Override
    public Optional<User> findById(int id) {
        Query query = this.em.createQuery("SELECT u FROM User u WHERE u.id =:id");
        query.setParameter("id", id);
        return (Optional<User>) query.setMaxResults(1).getResultList().stream().findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Query query = this.em.createQuery("SELECT u FROM User u WHERE u.name =:username");
        query.setParameter("username", username);
        return (Optional<User>) query.setMaxResults(1).getResultList().stream().findFirst();
    }

    @Override
    public void save(User user) {
        this.em.persist(user);
    }
}
