package com.be_project.repository;

import com.be_project.entity.Exchange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;

public interface IExchangeRepo extends JpaRepository<Exchange, Long> {
    @Query("SELECT e FROM Exchange e WHERE (e.postBuy.account.id = :accountId " +
            "OR e.postSell.account.id = :accountId) " +
            "AND e.status LIKE CONCAT('%', :status, '%') " +
            "AND e.postSell.title LIKE CONCAT('%', :postSell, '%') " +
            "AND e.postBuy.title LIKE CONCAT('%', :postBuy, '%') " +
            "AND e.createdAt BETWEEN :startDate AND :endDate ORDER BY e.createdAt DESC")
    Page<Exchange> getAllByAccountId(@Param("accountId") long accountId,
                                     @Param("status") String status,
                                     @Param("postSell") String postSell,
                                     @Param("postBuy") String postBuy,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate,
                                     Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Exchange e SET e.status = 'Đã hủy' " +
            "WHERE (e.postBuy.id = :postSellId " +
            "OR (e.postSell.id = :postSellId AND e.postBuy.id != :postBuyId)) " +
            "OR (e.postSell.id = :postBuyId " +
            "OR (e.postSell.id != :postSellId AND e.postBuy.id = :postBuyId))")
    void removeAll(@Param("postSellId") long postSellId,
                   @Param("postBuyId") long postBuyId);
}
