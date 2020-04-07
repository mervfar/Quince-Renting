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

    public List<DriverLicense> findAll(){
        return driverLicenseRepository.findAll();
    }
    public DriverLicense findById(Long id){
        return driverLicenseRepository.findById(id).
                orElseThrow(() -> new ResourceAccessException("License with " + id + " not found"));
    }
    public DriverLicense findByTCno(long TC){
        return driverLicenseRepository.findByTCno(TC).
                orElseThrow(() -> new ResourceAccessException("License with " + TC + " not found"));
    }

    public DriverLicense findByDocumentNo(long documentNo){
        return driverLicenseRepository.findByDocumentNo(documentNo).
        orElseThrow(() -> new ResourceAccessException("License with " + documentNo + " not found"));
    }
    public List<DriverLicense> findByIntern(boolean intern){
        return driverLicenseRepository.findByIntern(intern);
    }
    public List<DriverLicense> findByOffice(String office){
        return driverLicenseRepository.findByOffice(office);
    }
    public List<DriverLicense> findByValid(boolean isValid){
        return driverLicenseRepository.findByValid(isValid);
    }
    public DriverLicense saveDriverLicense(DriverLicense license){
        return driverLicenseRepository.save(license);
    }
    public void deleteDriverLicense(DriverLicense license){
        DriverLicense driverLicense=driverLicenseRepository.findById(license.getId()).
                orElseThrow(() -> new ResourceAccessException("License with " + license.getId() + " not found"));
         driverLicenseRepository.delete(driverLicense);

    }
}
