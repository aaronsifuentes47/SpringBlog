package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String landing() {
        return "home";
    }

    @PostMapping
    public String showHomeName(@RequestParam int id, Model vModel) {
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        vModel.addAttribute("name", userSession.getUsername());
        return "home";

    }


}
