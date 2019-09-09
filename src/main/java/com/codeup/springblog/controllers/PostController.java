package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index(Model vModel) {
        ArrayList<Post> posts = new ArrayList<>();
        Post stuff = new Post(2,"crappy stuff", "take my junk");
        Post goods = new Post(3,"good stock", "High Quality goods for sale");
        posts.add(stuff);
        posts.add(goods);
        vModel.addAttribute("posts", posts);
        return "posts/index";
    }
    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individual(@PathVariable int id, Model viewModel) {
        Post post = new Post(1,"wonderful stuff", "my stuff that I have");
        viewModel.addAttribute("post", post);
        return "show";
    }
    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String viewCreate() {
        return "view create page!";
    }
    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String actualCreate() {
        return "create post!";
    }
}
