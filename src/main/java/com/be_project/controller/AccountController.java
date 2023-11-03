package com.be_project.controller;

import com.be_project.entity.Account;
import com.be_project.entity.dto.FilterDto;
import com.be_project.service.IAccountService;
import com.be_project.service.IExchangeService;
import com.be_project.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IPostService postService;
    @Autowired
    private IExchangeService exchangeService;


    @GetMapping("/{accountId}")
    public ResponseEntity<?> getById(@PathVariable long accountId) {
        try {
            return ResponseEntity.ok(accountService.getById(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{accountId}/posts")
    public ResponseEntity<?> getAllPostsByAccountId(@PathVariable Long accountId,
                                                    @RequestBody FilterDto filterDto,
                                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(postService.getAllByAccountId(accountId, filterDto.getStatus(), filterDto.getTitle(), filterDto.getStartDate(), filterDto.getEndDate(), page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{accountId}/messages")
    public ResponseEntity<?> listUserAndUnreadMessage(@PathVariable long accountId) {
        try {
            return ResponseEntity.ok(accountService.listUserAndUnreadMessage(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{accountId}/messages/search")
    public ResponseEntity<?> findAllByUsernameContainsAndNotAccountLogin(@PathVariable long accountId,
                                                                         @RequestParam("username") String username) {
        try {
            return ResponseEntity.ok(accountService.findAllByUsernameContainsAndNotAccountLogin(username, accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<?> editAccount(@PathVariable int accountId, @RequestBody Account accountEdit) {
        try {
            return ResponseEntity.ok(accountService.editAccount(accountId, accountEdit));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{accountId}/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Account accountEdit, @PathVariable long accountId) {
        try {
            accountService.changePassword(accountId, accountEdit.getPassword());
            return ResponseEntity.ok("Change password successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{accountId}/check-password")
    public boolean checkPassword(@PathVariable long accountId, @RequestBody Account accountEdit) {
        return accountService.checkPassword(accountId, accountEdit.getPassword());
    }

    @PostMapping("{accountId}/exchanges")
    public ResponseEntity<?> getAllExchangesByAccount(@PathVariable long accountId,
                                                      @RequestBody FilterDto filterDto,
                                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(exchangeService.getAllByAccountId(accountId, filterDto.getStatus(), filterDto.getPostSell(), filterDto.getPostBuy(), filterDto.getStartDate(), filterDto.getEndDate(), page, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
