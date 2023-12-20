package com.example.blog.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private Integer postId;
    @Column(length = 100,nullable = false, name="post_title")
    private String title;
    @Column(length = 1000)
    private String content;
    private String imageName;
    private Date addedDate;


//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;
@ManyToOne
@JoinColumn(name = "category_id")
//@JsonBackReference
private Category category;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();



}
