package cz.uhk.ppro.demo.controller;

import cz.uhk.ppro.demo.Model.Demo;
import cz.uhk.ppro.demo.Service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
public class IndexController {



    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String showIndex() {
        return "index";
    }



}
