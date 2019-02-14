package cz.uhk.ppro.demo.Repository.jpa;

import cz.uhk.ppro.demo.Model.ShoppingList;
import cz.uhk.ppro.demo.Repository.ShoppingListRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class ShoppingListRepositoryImpl implements ShoppingListRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<ShoppingList> findById(int id) {
        Query query = this.em.createQuery("SELECT l FROM ShoppingList l WHERE l.id =:id");
        query.setParameter("id", id);
        return (Optional<ShoppingList>) query.setMaxResults(1).getResultList().stream().findFirst();
    }


    @Override
    @Transactional
    public void save(ShoppingList shoppingList) {
        Optional<ShoppingList> d = this.findById(shoppingList.getId());
        if (d.isPresent()) {
            em.merge(shoppingList);
        } else {
            em.persist(shoppingList);
        }
    }

    @Override
    public List<ShoppingList> findAll() {
        Query query = this.em.createQuery("SELECT d FROM ShoppingList d");
        return query.getResultList();
    }

    @Override
    public List<ShoppingList> findAllByUSerId(int id) {
        Query query = this.em.createQuery("SELECT l FROM ShoppingList l WHERE l.user.id =:id order by l.createdAt desc");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void removeItem(ShoppingList shoppingList) {
        if (em.contains(shoppingList)) {
            em.remove(shoppingList);
        } else {
            ShoppingList deletedItem = em.getReference(shoppingList.getClass(), shoppingList.getId());
            em.remove(deletedItem);
        }
    }
}
