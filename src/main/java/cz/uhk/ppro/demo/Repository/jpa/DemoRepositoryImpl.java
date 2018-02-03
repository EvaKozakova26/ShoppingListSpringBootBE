package cz.uhk.ppro.demo.Repository.jpa;

import cz.uhk.ppro.demo.Model.Demo;
import cz.uhk.ppro.demo.Repository.DemoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class DemoRepositoryImpl implements DemoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Demo> findById(int id) {
        Query query = this.em.createQuery("SELECT d FROM Demo d WHERE d.id =:id");
        query.setParameter("id", id);
        return (Optional<Demo>) query.setMaxResults(1).getResultList().stream().findFirst();
    }


    @Override
    @Transactional
    public void save(Demo demo) {
        Optional<Demo> d = this.findById(demo.getId());
        if(d.isPresent()) {
            em.merge(demo);
        } else {
            em.persist(demo);
        }
    }
}
