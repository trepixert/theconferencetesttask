package waveaccess.theconferencetesttask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/main")
    public String main(){ return "main";}
    @GetMapping({"/","/home"})
    public String home(){ return "home";}
}
