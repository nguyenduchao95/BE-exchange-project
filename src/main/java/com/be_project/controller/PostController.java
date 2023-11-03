package com.be_project.controller;

import com.be_project.entity.Account;
import com.be_project.entity.dto.PostDTO;
import com.be_project.service.IAccountService;
import com.be_project.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    IAccountService iAccountService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "status", defaultValue = "") String status,
                                    @RequestParam(name = "username", defaultValue = "") String username,
                                    @RequestParam(name = "title", defaultValue = "") String title,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "12") int size) {
        try {
            return ResponseEntity.ok(postService.getAll(status, username, title, page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getById(@PathVariable Long postId) {
        try {
            return ResponseEntity.ok(postService.getById(postId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails1 = (UserDetails) userDetails;
        Account account = iAccountService.getAccountByUsername(userDetails1.getUsername());
        return new ResponseEntity<>(postService.createPost(account, postDTO), HttpStatus.OK);
    }
}
