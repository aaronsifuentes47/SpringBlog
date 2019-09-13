package com.codeup.springblog.models;

import org.hibernate.validator.constraints.Length;
import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id @GeneratedValue
    private long id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String body;
    @OneToOne
    private User user;


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
//    private List<AdImage> images;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "ads_categories",
//            joinColumns = {@JoinColumn(name="ad_id")},
//            inverseJoinColumns = {@JoinColumn(name = "category_id")}
//    )
//    private List<AdCategory> categories;


    public Post(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body){
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
