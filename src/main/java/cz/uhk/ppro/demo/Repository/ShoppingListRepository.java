package cz.uhk.ppro.demo.Repository;

import cz.uhk.ppro.demo.Model.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(int id);

    void save(ShoppingList shoppingList);

    List<ShoppingList> findAll();

    List<ShoppingList> findAllByUSerId(int id);

    void removeItem(ShoppingList shoppingList);

}
