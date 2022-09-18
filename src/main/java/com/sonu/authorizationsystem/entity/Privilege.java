package com.sonu.authorizationsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

}