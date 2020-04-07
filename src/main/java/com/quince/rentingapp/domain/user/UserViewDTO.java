package com.quince.rentingapp.domain.user;

import lombok.Data;

@Data
public class UserViewDTO {
    private Long id;
    private String username;
    private String email;
    private String imageUrl;
    private long TCno;
    private int birthDate;
    private Role userRole;
}
