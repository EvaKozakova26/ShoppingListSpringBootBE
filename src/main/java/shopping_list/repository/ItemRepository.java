package shopping_list.repository;

import shopping_list.model.Item;
import shopping_list.model.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Optional<Item> findById(int id);

    void save(Item item);

    List<Item> findAllByListId(ShoppingList shoppingList);

    void removeItem(Item item);

    void changeState(Item item);

    Optional<Item> findItem(Item item);
}
