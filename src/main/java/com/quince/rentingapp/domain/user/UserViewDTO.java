package com.quince.rentingapp.domain.user;

import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import lombok.Data;

@Data
public class UserViewDTO {
    private String username;
    private String email;
    private String imageUrl;
    private String phoneNumber;
    private Role userRole;
    private DriverLicense driverLicense;
}
