package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.Item;
import cz.uhk.ppro.demo.Model.ShoppingList;
import cz.uhk.ppro.demo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
