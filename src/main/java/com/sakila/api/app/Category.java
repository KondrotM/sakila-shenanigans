package com.sakila.api.app;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int _id;

    @Column(name = "name")
    String name;

    public Category(int _id, String name){
        this._id = _id;
        this.name = name;
    }

    public Category(){}
}
