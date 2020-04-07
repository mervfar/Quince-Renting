package com.quince.rentingapp.controller;

import com.quince.rentingapp.domain.user.User;
import com.quince.rentingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public List<User> listUsers() {
        return  userService.findAll();
    }
    @PostMapping("/byId")
    public User listById(@RequestParam(value = "userId") Long userId){
        return userService.findById(userId);
    }
    @PostMapping ("/byTCno")
    public User listByTcNo(@RequestParam(value = "TCNO") long tcno){
        return userService.findByTCNo(tcno);
    }

    @PostMapping("/byBirthYear")
    public List<User> lisByBirthYear(@RequestParam(value = "year") double year){
        return userService.findByBirthDate(year);
    }
    @PostMapping("/byUsename")
    public User findByUsername(@RequestParam(value = "username") String username){
        return userService.findByUsername(username);
    }
    @PostMapping("/existByUsername")
    public Boolean ifExistByUsername(@RequestParam(value = "username") String username){
        return userService.existsByUsername(username);
    }
    @PostMapping("/existByEmail")
    public Boolean ifExistByEmail(@RequestParam(value = "email") String email){
        return userService.existsByEmail(email);
    }
}
