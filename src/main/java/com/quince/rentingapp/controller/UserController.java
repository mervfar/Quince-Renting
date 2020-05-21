package com.quince.rentingapp.controller;

import com.google.api.client.util.Lists;
import com.quince.rentingapp.domain.Utils;
import com.quince.rentingapp.domain.driverLicense.DriverLicense;
import com.quince.rentingapp.domain.driverLicense.DriverLicenseAddDTO;
import com.quince.rentingapp.domain.driverLicense.DriverLicenseViewDTO;
import com.quince.rentingapp.domain.user.User;
import com.quince.rentingapp.domain.user.UserViewDTO;
import com.quince.rentingapp.service.CurrentUserService;
import com.quince.rentingapp.service.DriverLicenseService;
import com.quince.rentingapp.service.UploadService;
import com.quince.rentingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CurrentUserService currentUser;
    private final UploadService uploadService;


    @PostMapping("/all")
    public List<UserViewDTO> listUsers() {
        return convertToViewDTO(userService.findAll()) ;
    }
    @PostMapping("/current")
    public Map<String,Object> currentUser(){
        HashMap<String,Object> result =new HashMap<>();
        User user =userService.findById(currentUser.getCurrentUser().getId());
        result.put("user",Utils.mapper(user,UserViewDTO.class));
        DriverLicense userLicense =user.getDriverLicense();
        if(userLicense!=null){
            result.put("driverLicense",Utils.mapper(userLicense, DriverLicenseViewDTO.class));
        } else{
            result.put("driverLicense",null);
        }
        return result;
    }
    @PostMapping("/byId")
    public UserViewDTO findById(@RequestParam(value = "userId") Long userId){
        return Utils.mapper(userService.findById(userId),UserViewDTO.class);
    }

    @PostMapping("/byUsename")
    public UserViewDTO findByUsername(@RequestParam(value = "username") String username){
        return Utils.mapper(userService.findByUsername(username),UserViewDTO.class);
    }
    @PostMapping("/existByUsername")
    public Boolean ifExistByUsername(@RequestParam(value = "username") String username){
        return userService.existsByUsername(username);
    }
    @PostMapping("/existByEmail")
    public Boolean ifExistByEmail(@RequestParam(value = "email") String email){
        return userService.existsByEmail(email);
    }
    @PostMapping("/edit")
    public Boolean updateProfilePicture(@RequestParam(value = "file") MultipartFile file) throws IOException {
        User user =userService.findById(currentUser.getCurrentUser().getId());
        String imageUrl=uploadService.uploadFile(file,user.getUsername());
        user.setImageUrl(imageUrl);
        userService.saveUser(user);
        return true;
    }
    private List<UserViewDTO> convertToViewDTO(List<User> userList){
        List<UserViewDTO> userViewDTOS=Lists.newArrayList();
        for (User user:userList){
            userViewDTOS.add(Utils.mapper(user,UserViewDTO.class));
        }
        return userViewDTOS;
    }
}
