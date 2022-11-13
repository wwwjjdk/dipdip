package com.example.dipdip.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
@Entity
@ToString
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, name = "name_of_file")
    String name;
    @Column(nullable = false, name = "size_of_file")
    Long size;
    @Column(nullable = false,name = "path_of_file")
    String link;
    @Column(nullable = false,name = "date_of_created")
    Date date;
}
