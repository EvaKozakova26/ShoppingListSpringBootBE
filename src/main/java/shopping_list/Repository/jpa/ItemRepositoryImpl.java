package shopping_list.Repository.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import shopping_list.Model.Item;
import shopping_list.Model.ShoppingList;
import shopping_list.Repository.ItemRepository;

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
    @Cacheable("item")
    public List<Item> findAllByListId(ShoppingList shoppingList) {
        Query query = this.em.createQuery("SELECT d FROM Item d where d.shoppingList = :shoppingList");
        query.setParameter("shoppingList", shoppingList);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void removeItem(Item item) {
        if (em.contains(item)) {
            em.remove(item);
        } else {
            Item deletedItem = em.getReference(item.getClass(), item.getId());
            em.remove(deletedItem);
        }
    }

    @Override
    @Transactional
    public void changeState(Item item) {
        em.merge(item);
    }

    @Override
    public Optional<Item> findItem(Item item) {
        Query query = this.em.createQuery("SELECT i FROM Item i WHERE i.name =:name " +
                "AND i.createdAt = :time AND i.count = :countI AND i.state =:state ");
        query.setParameter("name", item.getName());
        query.setParameter("time", item.getCreatedAt());
        query.setParameter("countI", item.getCount());
        query.setParameter("state", item.getState());
        return (Optional<Item>) query.setMaxResults(1).getResultList().stream().findFirst();
    }
}
