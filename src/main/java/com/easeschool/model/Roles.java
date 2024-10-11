package com.easeschool.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Roles extends BaseEntity{


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int roleId;

    private String roleName;
}
