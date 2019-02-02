package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.Item;
import cz.uhk.ppro.demo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;
@Controller
public class ValueController {

    private final ItemService itemService;

    @Autowired
    public ValueController(ItemService itemService) {
        this.itemService = itemService;
    }

    /*@RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView showNewForm(@ModelAttribute("demo") Item demo, ModelMap modelMap) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("new");
        return mav;
    }*/

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showNewForm(@ModelAttribute("item") Item item) {
        return "new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createNew(@ModelAttribute("item") @Valid Item item) throws IOException {
        itemService.saveItem(item);
        return "redirect:success";
    }

    @RequestMapping(value = "/success")
    public String showSuccess() {
        return "success";
    }

}
