package com.example.quince.Domains.User;


import lombok.Data;

@Data
public class UserAddDTO {
    private String username;
    private String password;
    private String email;
}
