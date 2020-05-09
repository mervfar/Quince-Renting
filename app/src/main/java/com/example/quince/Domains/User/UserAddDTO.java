package com.example.quince.Domains.User;


import lombok.Data;

@Data
public class UserAddDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
}
