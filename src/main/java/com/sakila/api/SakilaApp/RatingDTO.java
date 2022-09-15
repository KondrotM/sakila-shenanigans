package com.sakila.api.SakilaApp;

import javax.persistence.Column;

public class RatingDTO {
    private int rating_id;

    private int film_id;

    private int user_id;

    public RatingDTO() {
    }

    public RatingDTO(int rating_id, int film_id, int user_id) {
    }

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
