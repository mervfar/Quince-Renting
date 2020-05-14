package com.quince.rentingapp.repository;

import com.quince.rentingapp.domain.car.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBrand(String brand);
    List<Car> findByYear(double year);
    List<Car> findByCarBody(String body);
    List<Car> findByGear(String gear);
    List<Car> findByFuel(String CarFuelType);
    List<Car> findByAvailableEquals(boolean active);





}
