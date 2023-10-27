package com.be_project.service;

import com.be_project.entity.Post;
import org.springframework.data.domain.Page;

public interface IPostService {
    Page<Post> getAll(int page, int size);
    Post getById(long postId);
}
