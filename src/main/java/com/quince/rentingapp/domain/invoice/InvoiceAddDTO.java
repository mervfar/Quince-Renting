package com.quince.rentingapp.domain.invoice;

import lombok.Data;

@Data
public class InvoiceAddDTO {
    private String rentingStart;
    private String rentingEnd;
    private String pickUpLocation;
    private String dropOffLocation;
    private String carFee;
    private String carInfo;

}
