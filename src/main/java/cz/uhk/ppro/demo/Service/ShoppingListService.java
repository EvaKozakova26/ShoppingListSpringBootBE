package cz.uhk.ppro.demo.Service;

import cz.uhk.ppro.demo.Model.ShoppingList;
import cz.uhk.ppro.demo.Model.User;
import cz.uhk.ppro.demo.Repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingListService {

    private ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Transactional
    public ShoppingList saveItem(ShoppingList shoppingList) {
        shoppingListRepository.save(shoppingList);
        return shoppingList;
    }

    public List<ShoppingList> findItemsByUserId(User user) {
        return shoppingListRepository.findAllByUSerId(1);
    }

    @Transactional
    public void removeItem(ShoppingList shoppingList) {
        shoppingListRepository.removeItem(shoppingList);
    }


}
