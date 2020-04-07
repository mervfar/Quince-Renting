package com.quince.rentingapp.controller;

import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import com.quince.rentingapp.domain.driverLicense.LicenseCategory;
import com.quince.rentingapp.domain.driverLicense.LicenseType;
import com.quince.rentingapp.domain.user.BloodType;
import com.quince.rentingapp.service.DriverLicenseService;
import com.quince.rentingapp.service.LicenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/license")
public class DriverLicenseController {
    private final DriverLicenseService driverLicenseService;
    private final LicenseCategoryService licenseCategoryService;

    @PostMapping()
    public List<DriverLicense> listAll(){
        return driverLicenseService.findAll();
    }
    @PostMapping("/byTCno")
    public DriverLicense findByTcNo(@RequestParam(value = "TCNO") long tcno){
        return driverLicenseService.findByTCno(tcno);
    }
    @PostMapping("/byDocumentNo")
    public DriverLicense findByDocNo(@RequestParam(value = "docNo") long docNo){
        return driverLicenseService.findByDocumentNo(docNo);
    }
    @PostMapping("/byIntern")
    public List<DriverLicense> listInternLicenses(){
        return driverLicenseService.findByIntern(true);
    }
    @PostMapping("/byValid")
    public List<DriverLicense> listValidLicenses(){
        return driverLicenseService.findByValid(true);
    }
    @PostMapping("/save")
    public DriverLicense saveLicense(@RequestBody DriverLicense license){
        return driverLicenseService.saveDriverLicense(license);
    }
    @PostMapping("/updateCategory")
    public DriverLicense setCategoryForLicense(
            @RequestParam(value = "docNo") long docNo,
            @RequestParam(value = "givenDate") String givenDate,
            @RequestParam(value = "validDate") String validDate,
            @RequestParam(value = "extraInfo") String extraInfo,
            @RequestParam(value = "bloodType") BloodType bloodType,
            @RequestParam(value = "type") LicenseType type,
            @RequestParam(value = "active") boolean active){
        DriverLicense licenseToUpdate=driverLicenseService.findByDocumentNo(docNo);
        LicenseCategory  category =new LicenseCategory();
        category.setGivenDate(givenDate);
        category.setValidDate(validDate);
        category.setExtraInfo(extraInfo);
        category.setBloodType(bloodType);
        category.setType(type);
        category.setActive(active);
        licenseCategoryService.saveLicenseCategory(category);
        licenseToUpdate.getLicenseCategoryList().add(category);
        return driverLicenseService.saveDriverLicense(licenseToUpdate);
    }
    @DeleteMapping("/deleteByDocNo")
    public String deleteLicenseByDocNo(@RequestParam(value = "docNo") long docNo){
        DriverLicense license=driverLicenseService.findByDocumentNo(docNo);
        driverLicenseService.deleteDriverLicense(license);
        return  "{\"result\":\"OK\"}";
    }
}
