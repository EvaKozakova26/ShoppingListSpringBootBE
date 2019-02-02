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
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Item createNew(@RequestBody Item item) {
        System.out.println("called + " + item.getName());
        return itemService.saveItem(item);
    }

    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Item deleteItem(@RequestBody Item item) {
        itemService.removeItem(item);
        return item;
    }

    @CrossOrigin
    @RequestMapping(value = "/check", method = RequestMethod.PUT)
    public Item checkItem(@RequestBody Item item) {
        itemService.changeState(item);
        return item;
    }

    @RequestMapping(value = "/success")
    public String showSuccess() {
        return "success";
    }

}
