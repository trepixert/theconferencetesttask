package waveaccess.theconferencetesttask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import waveaccess.theconferencetesttask.models.Role;
import waveaccess.theconferencetesttask.models.User;
import waveaccess.theconferencetesttask.repo.UserRepo;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        Optional<User> userFromDB = userRepo.findUserByUsername(user.getUsername());
        if(userFromDB.isPresent()){
            model.put("message","User already exists");
            return "registration";
        }
        user.setRole(Role.LISTENER);
        userRepo.save(user);
        return "redirect:/login";
    }

}
