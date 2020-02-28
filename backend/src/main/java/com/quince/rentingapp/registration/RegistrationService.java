package com.quince.rentingapp.registration;

import com.quince.rentingapp.security.user_details.CustomUserDetails;
import com.quince.rentingapp.security.user_details.CustomUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final CustomUserDetailsRepository customUserDetailsRepository;
    @Autowired
    public RegistrationService(CustomUserDetailsRepository customUserDetailsRepository) {
        this.customUserDetailsRepository = customUserDetailsRepository;
    }

    public CustomUserDetails saveUser(CustomUserDetails customUserDetails){
        return customUserDetailsRepository.save(customUserDetails);
    }
}
