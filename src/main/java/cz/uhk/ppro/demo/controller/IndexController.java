package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    private final ItemService itemService;

    @Autowired
    public IndexController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(Model model) {
        return "index";
    }

}
