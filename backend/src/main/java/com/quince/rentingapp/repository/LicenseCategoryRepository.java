package com.quince.rentingapp.repository;

import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import com.quince.rentingapp.domain.driverLicense.LicenseCategory;
import com.quince.rentingapp.domain.driverLicense.LicenseType;
import com.quince.rentingapp.domain.user.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseCategoryRepository extends JpaRepository<LicenseCategory, Long> {
    List<LicenseCategory> findByBloodType(BloodType bloodType);
    List<LicenseCategory> findByType(LicenseType type);
    List<LicenseCategory> findByDriverLicense(DriverLicense license);
    List<LicenseCategory> findByActive(boolean isActive);
}
