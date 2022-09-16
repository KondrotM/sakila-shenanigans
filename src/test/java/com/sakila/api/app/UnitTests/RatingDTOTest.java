package com.sakila.api.app.UnitTests;

import com.sakila.api.app.RatingDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RatingDTOTest {

    @Test
    public void test_rating_initialise() {
        RatingDTO r = new RatingDTO();
    }

    @Test
    public void test_rating_full_initialise() {
        RatingDTO f = new RatingDTO(1, 1, 1);
    }


    @Test
    public void test_rating_id_get_set() {
        int expected = 1;

        RatingDTO r = new RatingDTO();
        r.setRatingIdDto(1);

        Assertions.assertEquals(expected, r.getRatingIdDto(), "Mismatch");
    }
    @Test
    public void test_rating_film_id_get_set() {
        int expected = 1;

        RatingDTO r = new RatingDTO();
        r.setFilmIdDto(1);

        Assertions.assertEquals(expected, r.getFilmIdDto(), "Mismatch");
    }
    @Test
    public void test_rating_user_id_get_set() {
        int expected = 1;

        RatingDTO r = new RatingDTO();
        r.setUserIdDto(1);

        Assertions.assertEquals(expected, r.getUserIdDto(), "Mismatch");
    }
}
