package com.be_project.service.impl;

import com.be_project.entity.Image;
import com.be_project.entity.Post;
import com.be_project.repository.IImageRepo;
import com.be_project.repository.IPostRepo;
import com.be_project.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepo imageRepo;
    @Autowired
    private IPostRepo postRepo;
    @Override
    public List<Image> findAllByPostId(long postId) {
        Post post = postRepo.findById(postId).get();
        List<Image> imageList = imageRepo.findAllByPostId(postId);
        List<Image> list = new ArrayList<>();
        list.add(new Image(imageList.size() + 1, post.getAvatar()));
        list.addAll(imageList);
        return list;
    }
}
