package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    @Autowired
    private EmailService emailService;

    public PostController(PostRepository postRepository, UserRepository userRepository){
        this.postDao = postRepository;
        this.userDao = userRepository;

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
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.findOne(id);
        boolean check = userSession.getId() == post.getUser().getId();
        vModel.addAttribute("editPerm", check);
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
        return "redirect:";
    }

    @PostMapping("posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.delete(id);
        return "redirect:/posts";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String viewCreate(Model vModel) {
        vModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String actualCreate(@ModelAttribute Post post) {
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(userSession.getId());
        post.setUser(userDB);

        Post savedPost = postDao.save(post);
        emailService.prepareAndSend(
                savedPost,
                "Post Created",
                String.format("Post with the id %d has been created",savedPost.getId())
        );
        return "redirect:/posts/" + savedPost.getId();
    }
}
