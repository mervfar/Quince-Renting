package com.quince.rentingapp.domain.invoice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.quince.rentingapp.domain.BaseEntity;
import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import com.quince.rentingapp.domain.user.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Data
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "InvoiceSeq")
@Table(name = "Invoice")
public class Invoice extends BaseEntity {

    @ManyToOne
    @JsonBackReference
    private User user;

    private String rentingTime;
    private String rentingStart;
    private String rentingEnd;
    private String pickUpLocation;
    private String dropOffLocation;
    private String totalFee;
    private String carFee;
    private String carInfo;
}
