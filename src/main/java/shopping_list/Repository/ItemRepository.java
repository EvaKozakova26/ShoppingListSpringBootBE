package shopping_list.Repository;

import shopping_list.Model.Item;
import shopping_list.Model.ShoppingList;

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
