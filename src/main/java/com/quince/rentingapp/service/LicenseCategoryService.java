package com.quince.rentingapp.service;

import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import com.quince.rentingapp.domain.driverLicense.LicenseCategory;
import com.quince.rentingapp.domain.driverLicense.LicenseType;
import com.quince.rentingapp.domain.user.BloodType;
import com.quince.rentingapp.repository.LicenseCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseCategoryService {
    private final LicenseCategoryRepository licenseCategoryRepository;

    public List<LicenseCategory> findAll(){
    return licenseCategoryRepository.findAll();
    }
    public LicenseCategory findById(long id){
        return licenseCategoryRepository.findById(id).
        orElseThrow(() -> new ResourceAccessException("License Category with " + id + " not found"));
    }
    public List<LicenseCategory> findByBloodType(BloodType blood){
        return licenseCategoryRepository.findByBloodType(blood);
    }
    public List<LicenseCategory> findByType(LicenseType type){
        return licenseCategoryRepository.findByType(type);
    }
    public List<LicenseCategory> findByDriverLicense(DriverLicense license){
        return licenseCategoryRepository.findByDriverLicense(license);
    }
    public List<LicenseCategory> findByActive(boolean isActive){{
        return licenseCategoryRepository.findByActive(isActive);
    }

    }
    public  void saveLicenseCategory(LicenseCategory licenseCategory){
        licenseCategoryRepository.save(licenseCategory);
    }
    public void delete(LicenseCategory licenseCategory){
        LicenseCategory category=licenseCategoryRepository.findById(licenseCategory.getId()).
                orElseThrow(() -> new ResourceAccessException("License Category with " + licenseCategory.getId() + " not found"));
        licenseCategoryRepository.delete(category);
    }
}
