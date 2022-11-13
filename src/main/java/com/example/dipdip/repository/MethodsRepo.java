package com.example.dipdip.repository;

import com.example.dipdip.model.RequestJwt;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface MethodsRepo {
    List<String> getAllFiles(String idOfClient);

    String uploadFile(String fileName,MultipartFile file) throws IOException;

    String deleteFile(String FileName);

    String downloadFile(String FileName);

    String changeOfFile(String FileName, String newFileName);

    HashMap<String, Object> login(RequestJwt requestJwt);

    String logout();
}
