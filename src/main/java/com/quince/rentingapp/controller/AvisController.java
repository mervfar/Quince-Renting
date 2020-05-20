package com.quince.rentingapp.controller;

import com.quince.rentingapp.domain.car.CarSearchQuery;
import com.quince.rentingapp.domain.car.CarViewDTO;
import com.quince.rentingapp.service.AvisService;
import com.quince.rentingapp.service.CarService;
import com.quince.rentingapp.service.CurrentUserService;
import com.quince.rentingapp.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avis")
public class AvisController {
    private final AvisService avisService;

    @PostMapping("/locations/{location}")
    public String getLocations(@PathVariable(value = "location") String keyword) throws JSONException, IOException {
        return avisService.getLoc(keyword);
    }
    @PostMapping("/availableCars")
    public List<CarViewDTO> getAvailableCars(@RequestBody CarSearchQuery searchQuery) throws IOException, JSONException {
        return avisService.getAvailableCars(
                searchQuery.getBrand(), searchQuery.getPickup_location(),
                searchQuery.getPickup_date(),searchQuery.getDropoff_date(),
                searchQuery.getDropoff_location(),searchQuery.getCountry_code());
    }
    @GetMapping("/availableCars")
    public CarSearchQuery getSearchQueryBody(){
        return new CarSearchQuery();
    }
}
