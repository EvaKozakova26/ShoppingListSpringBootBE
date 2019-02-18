package cz.uhk.ppro.demo.Service;

import cz.uhk.ppro.demo.Model.Item;
import cz.uhk.ppro.demo.Model.ShoppingList;
import cz.uhk.ppro.demo.Model.User;
import cz.uhk.ppro.demo.Repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private ShoppingListRepository shoppingListRepository;
    private ItemService itemService;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, ItemService itemService) {
        this.shoppingListRepository = shoppingListRepository;
        this.itemService = itemService;
    }

    @Transactional
    public ShoppingList saveItem(ShoppingList shoppingList) {
        shoppingListRepository.save(shoppingList);
        return shoppingList;
    }

    public List<ShoppingList> findItemsByUserId(User user) {
        return shoppingListRepository.findAllByUSerId(user.getId());
    }

    @Transactional
    public void removeItem(ShoppingList shoppingList) {
        shoppingListRepository.removeItem(shoppingList);
    }


    public ShoppingList createList(User user, List<Item> items) {
        List<Item> itemList = new ArrayList<>();
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shoppingList.setUser(user);
        saveItem(shoppingList);
        for (Item item : items) {
            Optional<Item> dbItem = itemService.findItem(item);
            if (dbItem.isPresent()) {
                dbItem.get().setShoppingList(shoppingList);
                itemService.saveItem(dbItem.get());
                itemList.add(dbItem.get());
            }
        }
        shoppingList.setItems(itemList);
        saveItem(shoppingList);
        return shoppingList;
    }
}
