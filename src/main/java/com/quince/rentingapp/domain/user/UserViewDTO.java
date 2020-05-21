package com.quince.rentingapp.domain.user;

import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import com.quince.rentingapp.domain.driverLicense.DriverLicenseViewDTO;
import lombok.Data;

@Data
public class UserViewDTO {
    private String username;
    private String email;
    private String imageUrl;
    private String phoneNumber;
    private String name;
    private String surname;
    private Role userRole;
    private DriverLicenseViewDTO driverLicense;
}
