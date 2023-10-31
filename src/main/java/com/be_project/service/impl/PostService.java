package com.be_project.service.impl;

import com.be_project.entity.Post;
import com.be_project.repository.IPostRepo;
import com.be_project.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepo postRepo;
    @Override
    public Page<Post> getAll(String status, String username, String title, int page, int size) {
        return postRepo.getAll(status, username, title, PageRequest.of(page, size));
    }

    @Override
    public Page<Post> getAllByAccountId(Long accountId, int page, int size) {
        return postRepo.findAllByAccountId(accountId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    @Override
    public Post getById(long postId) {
        return postRepo.findById(postId).get();
    }
}
