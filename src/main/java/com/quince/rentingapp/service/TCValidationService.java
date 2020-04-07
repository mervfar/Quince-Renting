package com.quince.rentingapp.service;

import com.quince.rentingapp.domain.TcValidation.TcValidationDTO;
import org.springframework.stereotype.Service;
import tr.gov.nvi.tckimlik.KPSPublic;


@Service
public class TCValidationService {
    public boolean checkValidity(TcValidationDTO person){
        return new KPSPublic().getKPSPublicSoap().tcKimlikNoDogrula(person.getTcNo(), person.getName(), person.getSurname(), person.getYear());
    }
}
