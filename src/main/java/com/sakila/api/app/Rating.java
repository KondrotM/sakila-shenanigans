package com.sakila.api.app;

import javax.persistence.*;

@Entity
@Table(name="film_rating")
public class Rating {
    // Attributes
    @Id
    @Column(name = "rating_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int rating_pk;

    @Column(name = "rating_id")
    int rating_id;

    @Column(name = "film_id")
    int film_id;

    @Column(name = "user_id")
    int user_id;

    // Constructors
    public Rating(int rating_id, int film_id, int user_id) {
        this.rating_id = rating_id;
        this.film_id = film_id;
        this.user_id = user_id;
    }

    public Rating() {
    }

    // Encapsulation
    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}

