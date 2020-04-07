package com.quince.rentingapp.domain.driverLicense;

import com.quince.rentingapp.domain.user.BloodType;
import lombok.Data;

@Data
public class LicenseCategoryAddDTO {
    private String givenDate;
    private String validDate;
    private String extraInfo;
    private BloodType bloodType;
    private LicenseType type;
}
