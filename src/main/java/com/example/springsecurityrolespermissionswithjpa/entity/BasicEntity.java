package com.example.springsecurityrolespermissionswithjpa.entity;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
