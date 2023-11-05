package com.be_project.service;

import com.be_project.entity.Post;
import com.be_project.entity.dto.PostDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IPostService {
    Page<Post> getAll(String status, String username, String title, int page, int size);
    Page<Post> getAllByAccountId(Long accountId, String status, String title, LocalDate startDate, LocalDate endDate, int page, int size);
    Post getByIdAndIncreaseViews(long postId);
    Post createPost(PostDto postDTO);
    Post editPost(long postId, PostDto postDto);
}
