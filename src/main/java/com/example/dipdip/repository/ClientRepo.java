package com.example.dipdip.repository;

import com.example.dipdip.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByName(String name);
}
