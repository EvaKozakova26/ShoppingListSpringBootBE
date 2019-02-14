package cz.uhk.ppro.demo.Repository;

import cz.uhk.ppro.demo.Model.Item;
import cz.uhk.ppro.demo.Model.ShoppingList;

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
