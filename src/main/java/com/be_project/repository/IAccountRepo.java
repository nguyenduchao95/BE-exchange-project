package com.be_project.repository;

import com.be_project.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IAccountRepo extends JpaRepository<Account, Long> {
    Account findByUsername(String username);

    Account findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM account where username= :username and password= :password")
    Account getAccountLogin(@Param("username") String username, @Param("password") String password);

}
