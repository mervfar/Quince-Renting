package com.quince.rentingapp.controller;

import com.google.api.client.util.Lists;
import com.quince.rentingapp.domain.Utils;
import com.quince.rentingapp.domain.driverLicense.*;
import com.quince.rentingapp.domain.user.BloodType;
import com.quince.rentingapp.domain.user.User;
import com.quince.rentingapp.domain.user.UserViewDTO;
import com.quince.rentingapp.service.CurrentUserService;
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
    private final CurrentUserService currentUser;

    @PostMapping()
    public List<DriverLicenseViewDTO> listAll(){
        return convertToViewDTO(driverLicenseService.findAll());
    }
    @PostMapping("/byTCno/{TCNO}")
    public DriverLicenseViewDTO findByTcNo(@PathVariable(value = "TCNO") long tcno){
        return Utils.mapper(driverLicenseService.findByTCno(tcno),DriverLicenseViewDTO.class);
    }
    @PostMapping("/byDocumentNo/{docNo}")
    public DriverLicenseViewDTO findByDocNo(@PathVariable(value = "docNo") long docNo){
        return Utils.mapper(driverLicenseService.findByDocumentNo(docNo),DriverLicenseViewDTO.class);
    }
    @PostMapping("/byIntern/{isIntern}")
    public List<DriverLicenseViewDTO> listInternLicenses(@PathVariable(value = "isIntern") boolean isIntern){
        return convertToViewDTO(driverLicenseService.findByIntern(isIntern));
    }
    @PostMapping("/byValid/{isValid}")
    public List<DriverLicenseViewDTO> listValidLicenses(@PathVariable(value = "isValid") boolean isValid){
        return convertToViewDTO(driverLicenseService.findByValid(isValid));
    }
    @GetMapping("/save")
    public DriverLicenseAddDTO savePrepare(){
        return new DriverLicenseAddDTO();
    }
    @PostMapping("/save")
    public String saveLicense(@RequestBody DriverLicenseAddDTO AddDTO){
        DriverLicense license=Utils.mapper(AddDTO,DriverLicense.class);
        User user=currentUser.getCurrentUser();
        license.setUser(user);
        driverLicenseService.saveDriverLicense(license);
        return  "{\"result\":\"OK\"}";
    }
    @GetMapping("/addCategory")
    public LicenseCategoryAddDTO categoryAddPrepare(){
        return new LicenseCategoryAddDTO();
    }
    @PostMapping("/addCategory/{docNo}")
    public String  setCategoryForLicense(
            @RequestBody LicenseCategoryAddDTO categoryAddDTO,
            @PathVariable(value = "docNo") long docNo){
        DriverLicense licenseToUpdate=driverLicenseService.findByDocumentNo(docNo);
        LicenseCategory category =Utils.mapper(categoryAddDTO,LicenseCategory.class);
        //category.setActive(false); //En başta false olarak alabiliriz belki
        licenseCategoryService.saveLicenseCategory(category);
        licenseToUpdate.getLicenseCategoryList().add(category);
         driverLicenseService.saveDriverLicense(licenseToUpdate);
        return  "{\"result\":\"OK\"}";
    }
    @DeleteMapping("/deleteByDocNo/{docNo}")
    public String deleteLicenseByDocNo(@PathVariable(value = "docNo") long docNo){
        DriverLicense license=driverLicenseService.findByDocumentNo(docNo);
        driverLicenseService.deleteDriverLicense(license);
        return  "{\"result\":\"OK\"}";
    }
    private List<DriverLicenseViewDTO> convertToViewDTO(List<DriverLicense> licenseList){
        List<DriverLicenseViewDTO> licenseViewDTOS= Lists.newArrayList();
        for (DriverLicense license:licenseList){
            licenseViewDTOS.add(Utils.mapper(license,DriverLicenseViewDTO.class));
        }
        return licenseViewDTOS;
    }
}
