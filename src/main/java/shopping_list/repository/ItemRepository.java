package shopping_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping_list.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findById(int id);

    List<Item> findAllByShoppingListId(int id);

    void delete(Item item);

}
