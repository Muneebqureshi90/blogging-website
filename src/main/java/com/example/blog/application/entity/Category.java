package com.example.blog.application.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @Column(name = "category",length = 100,nullable = false)
    private String categoryTitle;
    @Column(name = "description")
    private String categoryDescription;

//    @OneToMany(mappedBy = "category",cascade = {CascadeType.PERSIST , CascadeType.MERGE},fetch = FetchType.LAZY)
//    private List<Post> posts=new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JsonManagedReference
    @JsonIgnore
    private List<Post> posts;


}
