package com.quince.rentingapp.repository;

import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Long> {

   Optional<DriverLicense> findByTCno(long TC);
   Optional<DriverLicense> findByDocumentNo(long documentNo);
   List<DriverLicense> findByIntern(boolean intern);
   List<DriverLicense> findByOffice(String office);
   List<DriverLicense> findByValid(boolean isValid);
}
