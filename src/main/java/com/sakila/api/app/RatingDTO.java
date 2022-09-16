package com.sakila.api.app;

public class RatingDTO {
    private int ratingId;

    private int filmId;

    private int userId;

    public RatingDTO() {
    }

    public RatingDTO(int ratingId, int filmId, int userId) {
    }

    public int getRatingIdDto() {
        return ratingId;
    }

    public void setRatingIdDto(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getFilmIdDto() {
        return filmId;
    }

    public void setFilmIdDto(int filmId) {
        this.filmId = filmId;
    }

    public int getUserIdDto() {
        return userId;
    }

    public void setUserIdDto(int userId) {
        this.userId = userId;
    }
}
