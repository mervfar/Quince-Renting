package com.quince.rentingapp.domain.invoice;

import lombok.Data;

@Data
public class InvoiceViewDTO {
    private String rentingTime;
    private String rentingStart;
    private String rentingEnd;
    private String pickUpLocation;
    private String dropOffLocation;
    private String totalFee;
    private String carFee;
    private String carInfo;
    private String createdDate;
}
