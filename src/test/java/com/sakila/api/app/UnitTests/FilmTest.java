package com.sakila.api.app.UnitTests;

import com.sakila.api.app.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FilmTest {

//    Film f = new Film(0, "African Egg", "A tale of a teacher chasing a SQL Developer in a box", 2006, "PG", 50);

    @Test
    public void test_film_initialise() {
        Film f = new Film();
        Assertions.assertNotNull(f);
    }

    @Test
    public void test_film_partial_initialise() {
        Film f = new Film(0, "African Egg");
        Assertions.assertNotNull(f);
    }

    @Test
    public void test_film_full_initialise() {
        Film f = new Film(123, "Shrek License", "A tale of a teacher chasing a SQL Developer in a box", 2006, "PG", 50);
        Assertions.assertNotNull(f);
    }


    @Test
    public void test_film_id_get_set() {
        int expected = 0;

        Film f = new Film();
        f.setId(0);

        Assertions.assertEquals(expected, f.getId(), "Mismatch");
    }
    @Test
    public void test_film_title_get_set() {
        String expected = "Stagecoach Armageddon";

        Film f = new Film();
        f.setTitle("Stagecoach Armageddon");

        Assertions.assertEquals(expected, f.getTitle(), "Mismatch");
    }
    @Test
    public void test_film_description_get_set() {
        String expected = "A fateful yarn of a singer and a dinosaur chasing Nicki-James in a speedboat";

        Film f = new Film();
        f.setDescription("A fateful yarn of a singer and a dinosaur chasing Nicki-James in a speedboat");

        Assertions.assertEquals(expected, f.getDescription(), "Mismatch");
    }
    @Test
    public void test_film_released_get_set() {
        int expected = 2006;

        Film f = new Film();
        f.setReleased(2006);

        Assertions.assertEquals(expected, f.getReleased(), "Mismatch");
    }
    @Test
    public void test_film_rating_get_set() {
        String expected = "PG";

        Film f = new Film();
        f.setRating("PG");

        Assertions.assertEquals(expected, f.getRating(), "Mismatch");
    }
    @Test
    public void test_film_length_get_set() {
        int expected = 60;

        Film f = new Film();
        f.setLength(60);

        Assertions.assertEquals(expected, f.getLength(), "Mismatch");
    }

}
