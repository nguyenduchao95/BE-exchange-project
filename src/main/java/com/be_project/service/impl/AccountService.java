package com.be_project.service.impl;

import com.be_project.entity.Account;
import com.be_project.entity.Role;
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
        account.setAvatar("https://cdn-icons-png.flaticon.com/512/3177/3177440.png");
        accountRepo.save(account);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.getAccountByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add((GrantedAuthority) account.getRole());
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

    @Override
    public Account getById(long accountId) {
        return accountRepo.findById(accountId).get();
    }
    @Override
    public Account getAccountByUsername(String username) {
        return accountRepo.findByUsername(username);
    }
    @Override
    public Account editAccount(long accountId, Account accountEdit) {
        Account account = accountRepo.findById(accountId).get();
        account.setName(accountEdit.getName());
        account.setPhone(accountEdit.getPhone());
        account.setAddress(accountEdit.getAddress());
        account.setAvatar(accountEdit.getAvatar());
        return accountRepo.save(account);
    }

    @Override
    public void changePassword(long accountId, String password) {
        Account account = accountRepo.findById(accountId).get();
        account.setPassword(password);
        accountRepo.save(account);
    }

    @Override
    public boolean checkPassword(long accountId, String password) {
        Account account = accountRepo.findById(accountId).get();
        return account.getPassword().equals(password);
    }
}
