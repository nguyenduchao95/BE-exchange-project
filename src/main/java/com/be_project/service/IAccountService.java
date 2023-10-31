package com.be_project.service;

import com.be_project.entity.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface IAccountService extends UserDetailsService {
    Account getAccountLogin(String username, String password);

    boolean register(Account account);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
