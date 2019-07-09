package waveaccess.theconferencetesttask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class UserAdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String adminPage(Model model){
        model.addAttribute("users",userService.findAll());
        return "admin";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id,Model model){
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:"+id));
        model.addAttribute("user",user);
        return "fragments/update-user";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid User user,
                         BindingResult result, Model model
    ) {
       if(result.hasErrors()){
           user.setId(id);
           return "fragments/update-user";
       }
       userService.update(user);
       model.addAttribute("users",userService.findAll());
       return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        model.addAttribute("users", userService.findAll());
        return "admin";
    }
}
