package com.be_project.service.impl;

import com.be_project.entity.Account;
import com.be_project.entity.Role;
import com.be_project.repository.IAccountRepo;
import com.be_project.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepo accountRepo;

    @Override
    public Account getAccountLogin(String username, String password) {
        return accountRepo.getAccountLogin(username, password);
    }

    @Override
    public boolean register(Account account) {
        Account account1 = accountRepo.findByUsername(account.getUsername());
        if (account1 != null){
            return false;
        }
        account.setRole(new Role(2));
        account.setStatus("Đang hoạt động");
        account.setAvatar("https://cdn1.iconfinder.com/data/icons/basic-ui-set-v5-user-outline/64/Account_profile_user_avatar_small-512.png");
        accountRepo.save(account);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(account.getRole());
        return new User(account.getUsername(), account.getPassword(), roles);
    }
}
