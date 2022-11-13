package com.example.dipdip.repository;

import com.example.dipdip.model.Client;
import com.example.dipdip.model.MyFile;
import com.example.dipdip.model.RequestJwt;
import com.example.dipdip.security.JwtUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class MyRepositoryIml implements MethodsRepo, CommandLineRunner {
    private final static String UPLOAD_PATH = "C:\\Users\\gosha\\IdeaProjects\\dipdip\\upload\\";
    private final static String DIR_NAME = "upload";
    //кодирование
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //база
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private FileRepo fileRepo;

    //токен
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
       /* var roles = Stream.of("ROLE_CLIENT").
                map(n-> Role.builder()
                        .name(n)
                        .build())
                .collect(Collectors.toList());
        roleRepo.saveAll(roles);*/
        Client myClient = Client.builder()
                .name("gosha")
                .password(passwordEncoder.encode("gosha"))
                .role("ROLE_CLIENT")
                .date(new Date())
                .build();
        clientRepo.save(myClient);
    }


    @Override
    public List<String> getAllFiles(String idOfClient) {
        return null;
    }

    @Override
    public String uploadFile(String fileName,MultipartFile file)  {
        if (file.isEmpty()) {
            //todo exception 400
        }
        var filePost = new File(DIR_NAME);

        if (!filePost.exists()) {
            filePost.mkdir();
        }

        MyFile myFileSearch = fileRepo.findByName(fileName);
        if(myFileSearch != null){
         //todo exception 4..
        }
        var link = UPLOAD_PATH + fileName;

        try {
            file.transferTo(new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MyFile myFile = MyFile.builder()
                .name(fileName)
                .date(new Date())
                .size(file.getSize())
                .link(link)
                .build();
        fileRepo.save(myFile);

        return null;
    }

    @Override
    public String deleteFile(String FileName) {
        return null;
    }

    @Override
    public String downloadFile(String FileName) {
        return null;
    }

    @Override
    public String changeOfFile(String FileName, String newFileName) {
        return null;
    }

    @Override
    public HashMap<String, Object> login(RequestJwt requestJwt) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestJwt.getUsername(), requestJwt.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials", e);
        }
        var token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", requestJwt.getUsername());
        map.put("token", token);
        return map;
    }

    @Override
    public String logout() {
        return "Success logout";
    }
}
