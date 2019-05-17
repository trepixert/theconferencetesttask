package waveaccess.theconferencetesttask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import waveaccess.theconferencetesttask.models.Presentation;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.services.PresentationService;
import waveaccess.theconferencetesttask.services.UserService;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

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

        Iterator<User> i = presentation.getAuthor().iterator();
        while(i.hasNext()) {
            User author = i.next();
            author.getPresentations().remove(presentation);
            i.remove();
        }

        presentationService.delete(presentation);
        model.addAttribute("lectures", presentationService.findAll());
        return "redirect:/home";
    }

    @GetMapping("/addPresentation")
    public String addPresentation(){
        return "fragments/addPresentation";
    }

    /**
     *
     * Прошу простить за сей костыль, так как не слишком близко знаком с работой с json
     * @see CustomConverter#convert(String)
     */
    @PostMapping("/addPresentation")
    public String addPresentation(@Valid Presentation presentation, String date, @RequestParam("author") List<User> author, Model model){
        Presentation presentationFromDB = presentationService.findPresentationByTheme(presentation.getTheme());
        if(presentationFromDB!=null){
            model.addAttribute("message","User already exists");
            return "fragments/addPresentation";
        }
        author.forEach(presentation::addAuthor);
        date = date.replaceAll("T"," ");
        presentation.setDateTimeFormat(date);
        presentationService.save(presentation);
        author.forEach(user -> {
            user.addPresentation(presentation);
            userService.save(user);
        });
        model.addAttribute("lectures",presentationService.findAll());
        return "redirect:/home";
    }
}
