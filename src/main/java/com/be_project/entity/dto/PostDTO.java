package com.be_project.entity.dto;

import com.be_project.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class PostDTO {
    private long id;
    private String title;
    private String category;
    private String description;
    private String requirement;
    private String address;
    private String avatar;
    private List<Image> images;
}
