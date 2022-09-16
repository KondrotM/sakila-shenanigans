package com.sakila.api.app;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Category(){}
}
