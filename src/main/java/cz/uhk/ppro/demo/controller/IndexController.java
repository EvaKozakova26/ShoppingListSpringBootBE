package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.Demo;
import cz.uhk.ppro.demo.Service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class IndexController {

    private final DemoService demoService;

    @Autowired
    public IndexController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(Model model) {
        List<Demo> demoList = demoService.findDemos();
        model.addAttribute("demoList", demoList );
        return "index";
    }

}
