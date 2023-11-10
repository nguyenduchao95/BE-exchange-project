package com.be_project.repository;

import com.be_project.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface IPostRepo extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.status LIKE CONCAT('%', :status, '%') " +
            "AND p.account.username LIKE CONCAT('%', :username, '%') " +
            "AND p.title LIKE CONCAT('%', :title, '%') " +
            "AND p.categoryPost LIKE CONCAT('%', :categoryPost, '%')")
    Page<Post> getAll(@Param("status") String status,
                      @Param("username") String username,
                      @Param("title") String title,
                      @Param("categoryPost") String categoryPost,
                      Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.account.id = :accountId " +
            "AND p.categoryPost LIKE CONCAT('%', :categoryPost, '%') " +
            "AND p.status LIKE CONCAT('%', :status, '%')" +
            "AND p.title LIKE CONCAT('%', :title, '%') " +
            "AND p.createdAt BETWEEN :startDate AND :endDate")
    Page<Post> findAllByAccountId(@Param("accountId") Long accountId,
                                  @Param("categoryPost") String categoryPost,
                                  @Param("status") String status,
                                  @Param("title") String title,
                                  @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate,
                                  Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Post p SET p.status = :status WHERE p.account.id = :accountId " +
            "AND p.status != 'Đã trao đổi'")
    void changeStatusPostByAccountId(@Param("accountId") long accountId,
                                     @Param("status") String status);
}
