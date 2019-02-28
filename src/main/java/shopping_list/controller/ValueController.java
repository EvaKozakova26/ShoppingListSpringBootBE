package shopping_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping_list.model.Item;
import shopping_list.service.ItemService;

@RestController
public class ValueController {

    private final ItemService itemService;

    @Autowired
    public ValueController(ItemService itemService) {
        this.itemService = itemService;
    }

    @CrossOrigin
    @PostMapping(value = "api/newItem")
    public Item createNew(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    @CrossOrigin
    @DeleteMapping(value = "api/deleteItem")
    public Item deleteItem(@RequestBody Item item) {
        itemService.removeItem(item);
        return item;
    }

    @CrossOrigin
    @PutMapping(value = "api/checkItem")
    public Item checkItem(@RequestBody Item item) {
        itemService.changeState(item);
        return item;
    }


}
