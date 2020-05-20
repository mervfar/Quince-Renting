package com.quince.rentingapp.controller;

import com.google.api.client.util.Lists;
import com.quince.rentingapp.domain.Utils;
import com.quince.rentingapp.domain.car.*;
import com.quince.rentingapp.service.AvisService;
import com.quince.rentingapp.service.CarService;
import com.quince.rentingapp.service.CurrentUserService;
import com.quince.rentingapp.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public List<CarViewDTO> listAll() {
        return convertToViewDTO(carService.findAll());
    }

    @GetMapping("/byId/{id}")
    public CarViewDTO findById(@PathVariable(value = "id") Long id){
        return Utils.mapper(carService.findById(id),CarViewDTO.class);
    }
    @GetMapping("/byBrand/{brand}")
    public List<CarViewDTO> listByBrand(@PathVariable(value = "brand") String brand){
        return convertToViewDTO(carService.findByBrand(brand));
    }
    @GetMapping("/byYear/{year}")
    public List<CarViewDTO> listByYear(@PathVariable(value = "year") double year){
        return convertToViewDTO(carService.findByYear(year));
    }
    @GetMapping("/byBody/{body}")
    public List<CarViewDTO> listByBody(@PathVariable(value = "body") String body){
        return convertToViewDTO(carService.findByBody(body));
    }
    @GetMapping("/byGear/{gear}")
    public List<CarViewDTO> listByGear(@PathVariable(value = "gear") String gear){
        return convertToViewDTO(carService.findByTransmission(gear));
    }
    @GetMapping("/byFuel/{fuel}")
    public List<CarViewDTO> listByFuel(@PathVariable(value = "fuel") String fuel){
        return convertToViewDTO(carService.findByFuel(fuel));
    }
    @GetMapping("/availables/{isAvailable}")
    public List<CarViewDTO> listAvailables(@PathVariable(value = "isAvailable") boolean isAvailable){
        return convertToViewDTO(carService.findAvailableCars(isAvailable));
    }
    @PostMapping("/save")
    public String save(@RequestBody CarAddDTO carAddDTO,
                       @RequestParam(value = "image") MultipartFile image,
                       @RequestParam(value = "gltf",required = false) MultipartFile gltf) throws IOException {
        Car car=Utils.mapper(carAddDTO,Car.class);
        String username=currentUser.getCurrentUser().getUsername();
        String imageUrl=uploadService.uploadFile(image,username);
        car.setImageUrl(imageUrl);
        String gltfUrl=uploadService.uploadFile(gltf,username);
        car.setGltfUrl(gltfUrl);
        carService.saveCar(car);
        return "{\"result\":\"OK\"}";
    }
    @GetMapping("/save")
    public CarAddDTO savePrepare(){
        return new CarAddDTO();
    }
    @DeleteMapping("/delete/{carId}")
    public String deleteCar(@PathVariable(value = "carId") long carId){
       carService.deleteCar(carId);
        return "{\"result\":\"OK\"}";
    }
    private List<CarViewDTO> convertToViewDTO(List<Car> carList){
        List<CarViewDTO> CarViewDTOs= Lists.newArrayList();
        for (Car car:carList){
            CarViewDTOs.add(Utils.mapper(car,CarViewDTO.class));
        }
        return CarViewDTOs;
    }


}
