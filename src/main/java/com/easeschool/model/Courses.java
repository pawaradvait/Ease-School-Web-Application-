package com.easeschool.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Courses extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;

    private String name;

    private String fees;

    @ManyToMany(mappedBy = "courses" , fetch = FetchType.LAZY ,cascade = CascadeType.PERSIST)
    Set<Person> persons = new HashSet<Person>();

}
