package com.be_project.repository;

import com.be_project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post, Long> {
}
