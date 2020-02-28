package com.quince.rentingapp.controller;

import com.quince.rentingapp.service.RegistrationService;
import com.quince.rentingapp.security.user_details.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    public final RegistrationService registrationService;
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @PostMapping("/registration")
    public CustomUserDetails createUser(@RequestBody CustomUserDetails customUserDetails){
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final String encodedPassword = bCryptPasswordEncoder.encode(customUserDetails.getPassword());
        customUserDetails.setPassword(encodedPassword);
        return registrationService.saveUser(customUserDetails);
    }
}
