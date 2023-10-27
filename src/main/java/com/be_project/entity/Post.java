package com.be_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String category;
    private String description;
    private String requirement;
    private String address;
    private String avatar;
    private LocalDate createdAt = LocalDate.now();
    private String status;
    @ManyToOne
    private Account account;
    private int countLike;
}
