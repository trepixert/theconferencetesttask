package waveaccess.theconferencetesttask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import waveaccess.theconferencetesttask.models.Role;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.repo.UserRepo;
import waveaccess.theconferencetesttask.services.UserService;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User userFromDB = userService.findUserByUsername(user.getUsername());
        if(userFromDB!=null){
            model.put("message","User already exists");
            return "registration";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(Role.LISTENER);
        userService.save(user);
        return "redirect:/login";
    }

}
