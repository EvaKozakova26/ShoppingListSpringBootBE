package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.Item;
import cz.uhk.ppro.demo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("called + " + item.getName());
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
