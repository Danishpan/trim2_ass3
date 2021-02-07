package com.example.lastattempts.controller;

import com.example.lastattempts.domain.Card;
import com.example.lastattempts.domain.User;
import com.example.lastattempts.repos.CardRepo;
import com.example.lastattempts.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PasswordController {
    PasswordEncoder passwordEncoder;
    @Autowired
    public void PasswordEncoder(PasswordEncoder passwordEncoder)
    { this.passwordEncoder = passwordEncoder;}

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/change")
    public String main(@AuthenticationPrincipal User user,
                       Map<String, Object> model) {
        model.put("user", user);
        model.put("passwordName", user.getUsername());
        return "change_password";
    }

    @PostMapping("/change")
    public String changePassword(@RequestParam String username,
                               @RequestParam String newPassword,
                                 Map<String, Object> model){
        User user = userRepo.findByUsername(username);
        user.setPassword("$2a$10$ItPuOYRPZzNT1pRoMg0jYOIFR0JQg9OBogvWm8AheCEW9.pHWli1S");
        userRepo.save(user);
        return "redirect:/main";
    }
}
