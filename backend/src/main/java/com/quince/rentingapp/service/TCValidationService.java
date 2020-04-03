package com.quince.rentingapp.service;

import org.springframework.stereotype.Service;
import tr.gov.nvi.tckimlik.KPSPublic;


@Service
public class TCValidationService {
    public boolean checkValidity(String name, String surname, long tcno, int birthyear){
        return new KPSPublic().getKPSPublicSoap().tcKimlikNoDogrula(tcno, name, surname, birthyear);
    }
}
