package shopping_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shopping_list.Model.Item;
import shopping_list.Model.ShoppingList;
import shopping_list.Service.ItemService;

import java.util.List;

@CrossOrigin
@RestController
public class ItemListController {

    private final ItemService itemService;


    @Autowired
    public ItemListController(ItemService itemService) {
        this.itemService = itemService;
    }

    @CrossOrigin
    @PostMapping(value = "/getItems")
    public List<Item> showList(@RequestBody ShoppingList shoppingList) {
        return itemService.findItems(shoppingList);
    }
}
