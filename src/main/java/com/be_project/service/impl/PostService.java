package com.be_project.service.impl;

import com.be_project.entity.Post;
import com.be_project.repository.IPostRepo;
import com.be_project.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepo postRepo;
    @Override
    public Page<Post> getAll(int page, int size) {
        return postRepo.findAll(PageRequest.of(page, size));
    }

    @Override
    public Post getById(long postId) {
        return postRepo.findById(postId).get();
    }
}
