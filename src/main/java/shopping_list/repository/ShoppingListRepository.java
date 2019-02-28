package shopping_list.repository;

import shopping_list.model.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(int id);

    void save(ShoppingList shoppingList);

    List<ShoppingList> findAll();

    List<ShoppingList> findAllByUSerId(int id);

    void removeShoppingList(ShoppingList shoppingList);

}
