package com.sakila.api.SakilaApp.scraper;

import javax.persistence.*;

@Entity
@Table(name="film")
public class Film {
    // Attributes
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int _id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    public Film(int _id, String title, String description) {
        this._id = _id;
        this.title = title;
        this.description = description;
    }

    public Film(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
