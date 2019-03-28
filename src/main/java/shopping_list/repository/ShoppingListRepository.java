package shopping_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping_list.model.ShoppingList;

import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    ShoppingList findById(int id);

    ShoppingList save(ShoppingList shoppingList);

    List<ShoppingList> findAll();

    List<ShoppingList> findAllByUserId(int id);

}
