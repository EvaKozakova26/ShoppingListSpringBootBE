package cz.uhk.ppro.demo.Repository;

import cz.uhk.ppro.demo.Model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Optional<Item> findById(int id);

    void save(Item item);

    List<Item> findAll();
}
