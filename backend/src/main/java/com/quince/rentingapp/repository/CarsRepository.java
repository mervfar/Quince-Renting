package com.quince.rentingapp.repository;

import com.quince.rentingapp.domain.cars.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsRepository extends JpaRepository<Cars, String> {

}
