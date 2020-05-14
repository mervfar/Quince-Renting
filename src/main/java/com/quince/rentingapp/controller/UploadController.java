package com.quince.rentingapp.controller;

import com.quince.rentingapp.service.CurrentUserService;
import com.quince.rentingapp.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/uploadFile")
public class UploadController {
    private final UploadService uploadService;
    private final CurrentUserService currentUser;


    @PostMapping
    public String uploadFile(
            @RequestParam(value = "file") MultipartFile file) throws IOException {
        String username=currentUser.getCurrentUser().getUsername();
        return uploadService.uploadFile(file,username);
    }
}
