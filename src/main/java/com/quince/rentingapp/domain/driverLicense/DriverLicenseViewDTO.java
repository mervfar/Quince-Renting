package com.quince.rentingapp.domain.driverLicense;

import com.quince.rentingapp.domain.user.User;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class DriverLicenseViewDTO {
    private String name;
    private String surname;
    private String birthDate;
    private String birthLocation;
    private String dateOfIssue;
    private String validTime;
    private String office;
    private long TCno;
    private long documentNo;
    private boolean intern;
    private boolean valid;
    private User user;
    private Set<LicenseCategory> licenseCategory;
}
