package com.be_project.service.impl;

import com.be_project.entity.Account;
import com.be_project.entity.Image;
import com.be_project.entity.Post;
import com.be_project.entity.dto.PostDTO;
import com.be_project.repository.IImageRepo;
import com.be_project.repository.IPostRepo;
import com.be_project.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepo postRepo;
    @Autowired
    private IImageRepo imageRepo;

    @Override
    public Page<Post> getAll(String status, String username, String title, int page, int size) {
        return postRepo.getAll(status, username, title, PageRequest.of(page, size));
    }

    @Override
    public Page<Post> getAllByAccountId(Long accountId, String status, String title, LocalDate startDate, LocalDate endDate, int page, int size) {
        if (startDate == null) startDate = LocalDate.parse("1000-01-01");

        if (endDate == null) endDate = LocalDate.parse("9999-12-31");
        return postRepo.findAllByAccountId(accountId, status, title, startDate, endDate, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    @Override
    public Post getById(long postId) {
        return postRepo.findById(postId).get();
    }

    @Override
    public Post createPost(Account account, PostDTO postDTO) {
        Post post = new Post( postDTO.getTitle(), postDTO.getCategory(), postDTO.getDescription(), postDTO.getRequirement(), postDTO.getAddress(),
                postDTO.getAvatar(),account);
        for (Image i: postDTO.getImages()) {
            Image image = new Image(i.getUrl(),post);
            imageRepo.save(image);
        }
        return postRepo.save(post);
    }

    @Override
    public List<Image> getImagesByPost(long postId) {
        return imageRepo.findAllByPostId(postId);
    }

    @Override
    public Post editPost(PostDTO postDTO) {
        imageRepo.deleteByPostId(postDTO.getId());
        Post post = postRepo.getById(postDTO.getId());
        post.setAvatar(postDTO.getAvatar());
        post.setAddress(postDTO.getAddress());
        post.setCategory(postDTO.getCategory());
        post.setDescription(postDTO.getDescription());
        post.setTitle(postDTO.getTitle());
        post.setRequirement(postDTO.getRequirement());
        for (Image i:postDTO.getImages()) {
            imageRepo.save(new Image(i.getUrl(),post));
        }
        return postRepo.save(post);
    }
}
