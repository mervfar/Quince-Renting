package com.quince.rentingapp.service;

import com.quince.rentingapp.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarsServiceImplementation implements CarsService {

    @Autowired
    public CarsRepository carsRepository;

}
