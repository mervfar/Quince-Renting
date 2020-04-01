package com.quince.rentingapp.repository;

import com.quince.rentingapp.domain.car.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBrand(CarBrand brand);
    List<Car> findByYear(double year);
    List<Car> findByCarBody(CarBody body);
    List<Car> findByGear(CarTransmissionType gear);
    List<Car> findByFuel(CarFuelType CarFuelType);





}
