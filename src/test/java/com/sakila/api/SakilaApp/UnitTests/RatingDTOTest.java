package com.sakila.api.SakilaApp.UnitTests;

import com.sakila.api.SakilaApp.RatingDTO;
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
        r.setRating_id(1);

        Assertions.assertEquals(expected, r.getRating_id(), "Mismatch");
    }
    @Test
    public void test_rating_film_id_get_set() {
        int expected = 1;

        RatingDTO r = new RatingDTO();
        r.setFilm_id(1);

        Assertions.assertEquals(expected, r.getFilm_id(), "Mismatch");
    }
    @Test
    public void test_rating_user_id_get_set() {
        int expected = 1;

        RatingDTO r = new RatingDTO();
        r.setUser_id(1);

        Assertions.assertEquals(expected, r.getUser_id(), "Mismatch");
    }
}
