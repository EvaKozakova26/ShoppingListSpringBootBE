package shopping_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shopping_list.model.Item;
import shopping_list.model.ShoppingList;
import shopping_list.service.ItemServiceImpl;

import java.util.List;

@CrossOrigin
@RestController
public class ItemListController {

    private final ItemServiceImpl itemService;

    @Autowired
    public ItemListController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @CrossOrigin
    @PostMapping(value = "api/getItems")
    public List<Item> showList(@RequestBody ShoppingList shoppingList) {
        return itemService.findItems(shoppingList);
    }
}
