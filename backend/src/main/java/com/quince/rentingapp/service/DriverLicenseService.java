package com.quince.rentingapp.service;

import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import com.quince.rentingapp.repository.DriverLicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;

    private List<DriverLicense> findAll(){
        return driverLicenseRepository.findAll();
    }
    private DriverLicense findById(Long id){
        return driverLicenseRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("License with " + id + " not found"));
    }
    private DriverLicense findByTCno(int TC){
        return driverLicenseRepository.findByTCno(TC).
                orElseThrow(() -> new ResourceAccessException("License with " + TC + " not found"));
    }

    private DriverLicense findByDocumentNo(long documentNo){
        return driverLicenseRepository.findByDocumentNo(documentNo).
        orElseThrow(() -> new ResourceAccessException("License with " + documentNo + " not found"));
    }
    private List<DriverLicense> findByIntern(boolean intern){
        return driverLicenseRepository.findByIntern(intern);
    }
    private List<DriverLicense> findByOffice(String office){
        return driverLicenseRepository.findByOffice(office);
    }
    private List<DriverLicense> findByValid(boolean isValid){
        return driverLicenseRepository.findByValid(isValid);
    }
    private void saveDriverLicense(DriverLicense license){
        driverLicenseRepository.save(license);
    }
    private void deleteDriverLicense(DriverLicense license){
        DriverLicense driverLicense=driverLicenseRepository.findById(license.getId()).
                orElseThrow(() -> new ResourceAccessException("License with " + license.getId() + " not found"));
        driverLicenseRepository.delete(driverLicense);

    }
}
