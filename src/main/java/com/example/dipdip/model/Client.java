package com.example.dipdip.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, name = "name_of_client")
    String name;

    @Column(nullable = false, name = "password_of_client")
    String password;

    @Column(nullable = false, name = "date_of_created")
    Date date;

    @Column(nullable = false, name = "role_of_clients")
    String role;
}
