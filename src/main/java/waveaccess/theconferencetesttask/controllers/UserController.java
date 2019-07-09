package waveaccess.theconferencetesttask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import waveaccess.theconferencetesttask.models.Presentation;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.services.PresentationService;
import waveaccess.theconferencetesttask.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/home")
public class UserController {

    @Autowired
    private PresentationService presentationService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("lectures", presentationService.findAll());
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
    public String addPresentation() {
        return "fragments/addPresentation";
    }

    /**
     * Прошу простить за сей костыль, так как не слишком близко знаком с работой с json
     *
     * @see CustomConverter#convert(String)
     */
    @PostMapping("/addPresentation")
    public String addPresentation(@Valid Presentation presentation, String date, @RequestParam("author") List<User> author, Model model) {
        Presentation presentationFromDB = presentationService.findPresentationByTheme(presentation.getTheme());
        if (presentationFromDB != null) {
            model.addAttribute("message", "User already exists");
            return "fragments/addPresentation";
        }
        presentationService.save(presentation, date, author);
        model.addAttribute("lectures", presentationService.findAll());
        return "redirect:/home";
    }
}
