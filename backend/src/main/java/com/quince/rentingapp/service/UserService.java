package com.quince.rentingapp.service;

import com.quince.rentingapp.domain.user.User;
import com.quince.rentingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    private User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
