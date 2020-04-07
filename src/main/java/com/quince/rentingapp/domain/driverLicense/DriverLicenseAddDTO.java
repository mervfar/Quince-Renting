package com.quince.rentingapp.domain.driverLicense;

import lombok.Data;

@Data
public class DriverLicenseAddDTO {
    private String name;
    private String surname;
    private String birthDate;
    private String birthLocation;
    private String dateOfIssue;
    private String validTime;
    private String office;
    private long TCno;
    private long documentNo;
}
