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
    int ratingId;

    @Column(name = "film_id")
    int filmId;

    @Column(name = "user_id")
    int userId;

    // Constructors
    public Rating(int ratingId, int filmId, int userId) {
        this.ratingId = ratingId;
        this.filmId = filmId;
        this.userId = userId;
    }

    public Rating() {
    }

    // Encapsulation
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId){
        this.filmId = filmId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

