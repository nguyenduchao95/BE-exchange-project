package com.be_project.service;

import com.be_project.entity.Account;
import com.be_project.entity.dto.AccountAndMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface IAccountService extends UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Page<Account> findAllByStatusContainingAndUsernameContaining(String status, String username, int page, int size);
    void blockAccount(Long accountId);
    void unBlockAccount(Long accountId);

    List<AccountAndMessageDto> listUserAndUnreadMessage(long userId);
    List<Account> findAllByUsernameContainsAndNotAccountLogin(String username, long accountId);
}
