package com.codeup.springblog.repos;

import com.codeup.springblog.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface AdRepository extends CrudRepository<Post, Long> {

    Post findByTitle(String title);


//        private final AdRepository adDao;
//
//    public AdController(AdRepository adRepository) {
//        adDao = adRepository;
//    }
//
//    @GetMapping("/ads")
//    public String index(Model vModel) {
//        Iterable<Ad> ads = adDao.findAll();
//        vModel.addAttribute("ads", ads);
//        return "ads/index";
//    }
}
