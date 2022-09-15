package com.sonu.authorizationsystem.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ERole roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
