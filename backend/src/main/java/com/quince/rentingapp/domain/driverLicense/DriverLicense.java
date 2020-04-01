package com.quince.rentingapp.domain.driverLicense;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.quince.rentingapp.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "DriverLicenseSeq")
@Table(name = "DriverLicense")
public class DriverLicense extends BaseEntity {

    private String name;
    private String surname;
    private String birthDate;
    private String birthLocation;
    private String dateOfIssue;
    private String validTime;
    private String office;
    private int TCno;
    private long documentNo;
    private boolean intern;
    private boolean valid;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "driverLicense",cascade = CascadeType.REMOVE)
    private Set<LicenseCategory>  licenseCategoryList = new HashSet<>();

}
