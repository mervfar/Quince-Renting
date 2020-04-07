package com.quince.rentingapp.domain.driverLicense;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.quince.rentingapp.domain.BaseEntity;
import com.quince.rentingapp.domain.user.BloodType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "LicenseCategorySeq")
@Table(name = "LicenseCategory")
public class LicenseCategory extends BaseEntity {

    private String givenDate;
    private String validDate;
    private String extraInfo;
    private BloodType bloodType;
    private LicenseType type;
    private boolean active;


    @ManyToOne()
    @JsonBackReference
    private DriverLicense driverLicense;
}
