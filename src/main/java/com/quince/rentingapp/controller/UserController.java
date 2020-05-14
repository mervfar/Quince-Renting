package com.quince.rentingapp.controller;

import com.google.api.client.util.Lists;
import com.quince.rentingapp.domain.Utils;
import com.quince.rentingapp.domain.user.User;
import com.quince.rentingapp.domain.user.UserViewDTO;
import com.quince.rentingapp.service.CurrentUserService;
import com.quince.rentingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CurrentUserService currentUser;

    @PostMapping
    public List<UserViewDTO> listUsers() {
        return convertToViewDTO(userService.findAll()) ;
    }
    @PostMapping("/current")
    public UserViewDTO currentUser(){
        return Utils.mapper(currentUser.getCurrentUser(),UserViewDTO.class);
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
    private List<UserViewDTO> convertToViewDTO(List<User> userList){
        List<UserViewDTO> userViewDTOS=Lists.newArrayList();
        for (User user:userList){
            userViewDTOS.add(Utils.mapper(user,UserViewDTO.class));
        }
        return userViewDTOS;
    }
}
