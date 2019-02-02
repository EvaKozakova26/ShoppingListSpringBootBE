package cz.uhk.ppro.demo.Repository.jpa;

import cz.uhk.ppro.demo.Model.Item;
import cz.uhk.ppro.demo.Repository.ItemRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Item> findById(int id) {
        Query query = this.em.createQuery("SELECT d FROM Item d WHERE d.id =:id");
        query.setParameter("id", id);
        return (Optional<Item>) query.setMaxResults(1).getResultList().stream().findFirst();
    }


    @Override
    @Transactional
    public void save(Item item) {
        Optional<Item> d = this.findById(item.getId());
        if(d.isPresent()) {
            em.merge(item);
        } else {
            em.persist(item);
        }
    }

    @Override
    public List<Item> findAll() {
        Query query = this.em.createQuery("SELECT d FROM Item d");
        return query.getResultList();
    }
}
