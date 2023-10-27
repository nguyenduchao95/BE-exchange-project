package com.be_project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;
    @ManyToOne
    private Post post;
    public Image(String url, Post post){
        this.url = url;
        this.post = post;
    }

    public Image(int id, String url){
        this.url = url;
        this.id = id;
    }
}
