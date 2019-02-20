package shopping_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping_list.Model.Item;
import shopping_list.Service.ItemService;

@RestController
public class ValueController {

    private final ItemService itemService;

    @Autowired
    public ValueController(ItemService itemService) {
        this.itemService = itemService;
    }

    @CrossOrigin
    @PostMapping(value = "/new")
    public Item createNew(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    @CrossOrigin
    @DeleteMapping(value = "/delete")
    public Item deleteItem(@RequestBody Item item) {
        itemService.removeItem(item);
        return item;
    }

    @CrossOrigin
    @PutMapping(value = "/check")
    public Item checkItem(@RequestBody Item item) {
        itemService.changeState(item);
        return item;
    }


}
