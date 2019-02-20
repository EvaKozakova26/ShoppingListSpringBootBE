package shopping_list.Repository;

import shopping_list.Model.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(int id);

    void save(ShoppingList shoppingList);

    List<ShoppingList> findAll();

    List<ShoppingList> findAllByUSerId(int id);

    void removeShoppingList(ShoppingList shoppingList);

}
