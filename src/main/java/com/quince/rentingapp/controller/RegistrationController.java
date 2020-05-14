package com.quince.rentingapp.controller;

import com.quince.rentingapp.domain.Utils;
import com.quince.rentingapp.domain.user.Role;
import com.quince.rentingapp.domain.user.UserAddDTO;
import com.quince.rentingapp.service.RegistrationService;
import com.quince.rentingapp.domain.user.User;
import com.quince.rentingapp.service.UploadService;
import com.quince.rentingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserService userService;
    private final UploadService uploadService;



    @PostMapping
    public String  createUser(
            @RequestBody UserAddDTO addDTO,
            @RequestParam(value = "file",required = false) MultipartFile file) throws IOException {
        if (userService.existsByEmail(addDTO.getEmail())){
            if (userService.existsByUsername(addDTO.getUsername())){
                return "{\"result\":False,\"info\":\"There is already a user registered with the credentials provided!\"}";
            }
            return "{\"result\":False,\"info\":\"There is already a user registered with the email provided!\"}";
        }
        if (userService.existsByUsername(addDTO.getUsername())){
            return "{\"result\":False,\"info\":\"There is already a user registered with the username provided!\"}";
        }
        User newUser= Utils.mapper(addDTO,User.class);
        newUser.setUserRole(Role.MEMBER);
        if(file!=null){
            newUser.setImageUrl(uploadService.uploadFile(file,newUser.getUsername()));
        }
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final String encodedPassword = bCryptPasswordEncoder.encode(addDTO.getPassword());
        newUser.setPassword(encodedPassword);
        registrationService.saveUser(newUser);
        return "{\"result\":True,\"info\":\"User has been created!\"}";
    }

    @GetMapping
    public UserAddDTO getUserDto(){
        return new UserAddDTO();
    }
}
