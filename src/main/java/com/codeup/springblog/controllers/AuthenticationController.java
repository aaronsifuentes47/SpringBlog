package com.codeup.springblog.controllers;

        import com.codeup.springblog.models.User;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        try {
            User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return "redirect:/profile";
        } catch (Exception e){
        return "users/login";
        }
    }
}
