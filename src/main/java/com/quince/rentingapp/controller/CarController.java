package com.quince.rentingapp.controller;

import com.quince.rentingapp.domain.car.*;
import com.quince.rentingapp.domain.user.User;
import com.quince.rentingapp.service.CarService;
import com.quince.rentingapp.service.CurrentUserService;
import com.quince.rentingapp.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;
    private final UploadService uploadService;
    private final CurrentUserService currentUser;

    @GetMapping
    public List<Car> listApp() {
        return carService.findAll();
    }
    @GetMapping("/byId")
    public Car listById(@RequestParam(value = "id") Long id){
        return carService.findById(id);
    }
    @GetMapping("/byBrand")
    public List<Car> listByBrand(@RequestParam(value = "brand") CarBrand brand){
        return carService.findByBrand(brand);
    }
    @GetMapping("/byYear")
    public List<Car> listByYear(@RequestParam(value = "year") double year){
        return carService.findByYear(year);
    }
    @GetMapping("/byBody")
    public List<Car> listByBody(@RequestParam(value = "body") CarBody body){
        return carService.findByBody(body);
    }
    @GetMapping("/byGear")
    public List<Car> listByGear(@RequestParam(value = "gear") CarTransmissionType gear){
        return carService.findByTransmission(gear);
    }
    @GetMapping("/byFuel")
    public List<Car> listByFuel(@RequestParam(value = "fuel") CarFuelType fuel){
        return carService.findByFuel(fuel);
    }
    @GetMapping("/availables")
    public List<Car> listAvailables(@RequestParam(value = "isAvailable") boolean isAvailable){
        return carService.findAvailableCars(isAvailable);
    }
    @PostMapping("/save")
    public String saveCar(@RequestBody Car car) throws IOException {
        String username=currentUser.getCurrentUser().getUsername();
        String imageUrl=uploadService.uploadFile(car.getImageFile(),username);
        car.setImageUrl(imageUrl);
        String gltfUrl=uploadService.uploadFile(car.getGltfFile(),username);
        car.setGltfUrl(gltfUrl);
        carService.saveCar(car);
        return "{\"result\":\"OK\"}";
    }
    @DeleteMapping("/delete")
    public String deleteCar(@RequestParam(value = "carId") long carId){
       carService.deleteCar(carId);
        return "{\"result\":\"OK\"}";
    }


}
