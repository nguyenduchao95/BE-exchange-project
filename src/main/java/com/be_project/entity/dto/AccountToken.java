package com.be_project.entity.dto;

import com.be_project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AccountToken {
    private long id;
    private String username;
    private String name;
    private String status;
    private String avatar;
    private String phone;
    private String address;
    private Role role;
    private String token;

}
