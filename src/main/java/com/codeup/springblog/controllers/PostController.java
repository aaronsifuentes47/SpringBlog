package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postRepository, UserRepository userRepository){
        postDao = postRepository;
        userDao = userRepository;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String index(Model vModel) {
        vModel.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String individual(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.findOne(id));
        return "posts/show";
    }

    @RequestMapping(path = "posts/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model vModel) {
        Post post = postDao.findOne(id);
        vModel.addAttribute("post", post);
        return "posts/edit";
    }

    @RequestMapping(path = "posts/{id}/edit", method = RequestMethod.POST)
    public String editForm(@PathVariable long id,
                           @RequestParam(name="title")String title,
                           @RequestParam(name="body")String body) {
        Post updatePost = postDao.findOne(id);
        updatePost.setBody(body);
        updatePost.setTitle(title);
        postDao.save(updatePost);
        System.out.println("redirect:posts/" +updatePost.getId());
        return "redirect:";
    }

    @PostMapping("posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.delete(id);
        return "redirect:/posts";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String viewCreate() {
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String actualCreate(Model vModel) {
        User userDB = userDao.findOne(1L);
        vModel.addAttribute("post", new Post());
        return "redirect:/posts";
    }
}
