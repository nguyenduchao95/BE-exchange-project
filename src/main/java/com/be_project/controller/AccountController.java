package com.be_project.controller;
import com.be_project.service.IAccountService;
import com.be_project.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IPostService postService;

    @GetMapping("/{accountId}/posts")
    public ResponseEntity<?> getAllPostsByAccountId(@PathVariable Long accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(postService.getAllByAccountId(accountId, page, size));
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
}
