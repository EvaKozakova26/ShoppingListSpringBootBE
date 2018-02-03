package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.Demo;
import cz.uhk.ppro.demo.Service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.io.IOException;
@Controller
public class ValueController {

    private final DemoService demoService;

    @Autowired
    public ValueController(DemoService demoService) {
        this.demoService = demoService;
    }

    /*@RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView showNewForm(@ModelAttribute("demo") Demo demo, ModelMap modelMap) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("new");
        return mav;
    }*/

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showNewForm(@ModelAttribute("demo") Demo demo) {
        return "new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createNew(@ModelAttribute("demo") @Valid Demo demo) throws IOException {
        demoService.saveAdvert(demo);
        return "redirect:success";
    }

    @RequestMapping(value = "/success")
    public String showSuccess() {
        return "success";
    }

}
