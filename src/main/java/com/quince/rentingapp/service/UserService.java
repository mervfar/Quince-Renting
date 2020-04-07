package com.quince.rentingapp.service;

import com.quince.rentingapp.domain.user.User;
import com.quince.rentingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(Long id){
        return userRepository.findById(id).
        orElseThrow(() -> new ResourceAccessException("User with " + id + " not found"));
    }
    public User findByTCNo(long tcno){
        return userRepository.findByTCno(tcno);
    }
    public List<User> findByBirthDate(double year){
        return userRepository.findByBirthDate(year);
    }
    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }
}
