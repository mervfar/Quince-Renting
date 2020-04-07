package com.quince.rentingapp.controller;

import com.quince.rentingapp.domain.TcValidation.TcValidationDTO;
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
    public Boolean checkValidaiton(@RequestBody TcValidationDTO person){
        return tcValidationService.checkValidity(person);
    }
    @GetMapping
    public TcValidationDTO sentDto(){
        return new TcValidationDTO();
    }

}
