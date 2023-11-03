package com.be_project.service;

import com.be_project.entity.Account;
import com.be_project.entity.Post;
import com.be_project.entity.dto.PostDTO;
import org.springframework.data.domain.Page;

public interface IPostService {
    Page<Post> getAll(String status, String username, String title, int page, int size);
    Page<Post> getAllByAccountId(Long accountId, int page, int size);
    Post getById(long postId);
    Post createPost(Account account, PostDTO postDTO);
}
