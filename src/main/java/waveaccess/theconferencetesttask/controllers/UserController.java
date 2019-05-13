package waveaccess.theconferencetesttask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import waveaccess.theconferencetesttask.models.Presentation;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.services.PresentationService;
import waveaccess.theconferencetesttask.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/home")
public class UserController {

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        model.addAttribute("lectures",presentationService.findAll());
        return "home";
    }

    @GetMapping("delete/{id}")
    public String deletePresentation(@PathVariable("id") long id, Model model) {
        Presentation presentation = presentationService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid presentation Id:" + id));
        presentationService.delete(presentation);
        model.addAttribute("lectures", presentationService.findAll());
        return "redirect:/home";
    }

    @GetMapping("/addPresentation")
    public String addPresentation(){
        return "fragments/addPresentation";
    }

    @PostMapping("/addPresentation")
    public String addPresentation(@Valid Presentation presentation, String date, Model model){
        Presentation presentationFromDB = presentationService.findPresentationByTheme(presentation.getTheme());
        if(presentationFromDB!=null){
            model.addAttribute("message","User already exists");
            return "fragments/addPresentation";
        }
        date = date.replaceAll("T"," ");
        presentation.setDateTimeFormat(date);
        presentationService.save(presentation);
        model.addAttribute("lectures",presentationService.findAll());
        return "redirect:/home";
    }
}
