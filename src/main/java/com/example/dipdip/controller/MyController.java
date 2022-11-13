package com.example.dipdip.controller;

import com.example.dipdip.model.RequestJwt;

import com.example.dipdip.repository.MyRepositoryIml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/cloud")
public class MyController {
    @Autowired
     private MyRepositoryIml myRepositoryIml;

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody RequestJwt requestJwt) {
        return myRepositoryIml.login(requestJwt);
    }

    @PostMapping("/logout")
    public String logout(){
        //todo
        return myRepositoryIml.logout();
    }
    @PostMapping("/file")
    public String uploadFile(@RequestParam("fileName") String filename,@RequestParam("file") MultipartFile file) throws IOException {
        return myRepositoryIml.uploadFile(filename,file);
    }

}
