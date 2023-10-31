package com.be_project.service.impl;

import com.be_project.entity.Account;
import com.be_project.entity.dto.AccountAndMessageDto;
import com.be_project.repository.IAccountRepo;
import com.be_project.repository.IMessageRepo;
import com.be_project.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private IMessageRepo messageRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(account.getRole());
        return new User(account.getUsername(), account.getPassword(), roles);
    }

    @Override
    public Page<Account> findAllByStatusContainingAndUsernameContaining(String status, String username, int page, int size) {
        return accountRepo.findAllByStatusContainingAndUsernameContaining(status, username, PageRequest.of(page, size));
    }

    @Override
    public void blockAccount(Long accountId) {
        Account account = accountRepo.findById(accountId).get();
        if (account != null && account.getStatus().equals("Đang hoạt động")){
            account.setStatus("Bị khóa");
            accountRepo.save(account);
        }
    }

    @Override
    public void unBlockAccount(Long accountId) {
        Account account = accountRepo.findById(accountId).get();
        if (account != null && account.getStatus().equals("Bị khóa")){
            account.setStatus("Đang hoạt động");
            accountRepo.save(account);
        }
    }

    @Override
    public List<AccountAndMessageDto> listUserAndUnreadMessage(long userId) {
        List<Account> accounts = accountRepo.listUserMessage(userId);
        List<AccountAndMessageDto> list = new ArrayList<>();
        for (Account account : accounts){
            long count = messageRepo.countUnreadMessagesByAccountLoginIdAndSenderId(userId, account.getId());
            list.add(new AccountAndMessageDto(account, count));
        }
        return list;
    }

    @Override
    public List<Account> findAllByUsernameContainsAndNotAccountLogin(String username, long accountId) {
        return accountRepo.findAllByUsernameContainsAndNotAccountLogin(username, accountId);
    }
}
