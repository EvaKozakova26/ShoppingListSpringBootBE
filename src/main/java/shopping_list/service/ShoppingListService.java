package shopping_list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping_list.dto.ShoppingListDto;
import shopping_list.model.Item;
import shopping_list.model.ShoppingList;
import shopping_list.model.User;
import shopping_list.repository.ShoppingListRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {

    private ShoppingListRepository shoppingListRepository;
    private ItemServiceImpl itemService;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, ItemServiceImpl itemService) {
        this.shoppingListRepository = shoppingListRepository;
        this.itemService = itemService;
    }

    @Transactional
    public ShoppingList saveShoppingList(ShoppingList shoppingList) {
        shoppingListRepository.save(shoppingList);
        return shoppingList;
    }

    public List<ShoppingList> findItemsByUserId(User user) {
        return shoppingListRepository.findAllByUserId(user.getId());
    }

    @Transactional
    public void removeList(ShoppingList shoppingList) {
        shoppingListRepository.delete(shoppingList);
        List<Item> items = itemService.findItems(shoppingList);
        items.forEach(item -> itemService.removeItem(item));
    }


    public ShoppingList createList(User user, List<Item> items) {
        ShoppingList shoppingList = new ShoppingList();
        List<Item> itemList = new ArrayList<>();
        shoppingList.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shoppingList.setUser(user);
        saveShoppingList(shoppingList);
        items.forEach(item -> {
            Item dbItem = itemService.findById(item);
            if (dbItem != null) {
                dbItem.setShoppingList(shoppingList);
                itemService.saveItem(dbItem);
                itemList.add(dbItem);
            }
        });
        shoppingList.setItems(itemList);
        saveShoppingList(shoppingList);
        return shoppingList;
    }

    public ShoppingList updateList(ShoppingListDto shoppingListDto, User user) {
        ShoppingList shoppingList = shoppingListDto.getShoppingList();
        List<Item> itemList = new ArrayList<>();
        shoppingListDto.getItems().forEach(item -> {
            Item dbItem = itemService.findById(item);
            if (dbItem != null) {
                dbItem.setShoppingList(shoppingList);
                itemService.saveItem(dbItem);
                itemList.add(dbItem);
            }
        });
        shoppingList.setItems(itemList);
        shoppingList.setUser(user);
        return saveShoppingList(shoppingList);
    }
}
