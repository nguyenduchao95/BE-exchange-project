package com.be_project.repository;

import com.be_project.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPostRepo extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.status LIKE CONCAT('%', :status, '%') " +
            "AND p.account.username LIKE CONCAT('%', :username, '%') " +
            "AND p.title LIKE CONCAT('%', :title, '%')")
    Page<Post> getAll(@Param("status") String status,
                      @Param("username") String username,
                      @Param("title") String title,
                      Pageable pageable);
    Page<Post> findAllByAccountId(Long accountId, Pageable pageable);
}
