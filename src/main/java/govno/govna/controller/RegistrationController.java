package govno.govna.controller;

import govno.govna.model.Role;
import govno.govna.model.User;
import govno.govna.repository.MessageRepo;
import govno.govna.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.GeneratedValue;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String registration(){
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            model.put("message", "User exists!");
            return "registration"; }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }


}
