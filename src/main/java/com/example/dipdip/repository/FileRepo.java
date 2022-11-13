package com.example.dipdip.repository;

import com.example.dipdip.model.Client;
import com.example.dipdip.model.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<MyFile, Long> {
    MyFile findByName(String name);
}
