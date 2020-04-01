package com.quince.rentingapp.controller;

import com.quince.rentingapp.service.TCValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.parsers.ParserConfigurationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/validation")
public class TCValidationController {
    private final TCValidationService tcValidationService;

    @PostMapping
    public Boolean checkValidaiton(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "tc") long tcNo,
            @RequestParam(value = "year") int year) throws ParserConfigurationException {




        return tcValidationService.checkValidity(name,surname, tcNo,year);
    }

}
