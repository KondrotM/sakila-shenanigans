package com.sakila.api.SakilaApp.BDD;

import com.sakila.api.SakilaApp.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetFilmData {

    private SakilaAppApplication sakilaAppApplication;

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private RatingRepository ratingRepository;
    int id;
    Film testFilm;
    Film expected;

//    @BeforeEach
    public GetFilmData() {
        actorRepository = mock(ActorRepository.class);
        filmRepository = mock(FilmRepository.class);
        ratingRepository = mock(RatingRepository.class);
        sakilaAppApplication = new SakilaAppApplication(actorRepository, filmRepository, ratingRepository);

        expected = new Film(1, "African Egg");
    }


    @Given("I have a film ID")
    public void i_have_a_film_id() {
        id = 1;
        when(filmRepository.findById(id)).thenReturn(Optional.of(expected));
    }

    @Given("The ID has a corresponding movie in the metadata")
    public void the_id_has_a_corresponding_movie_in_the_metadata() {
    }

    @When("I fetch a movie by ID")
    public void i_fetch_a_movie_by_id() {
        Optional<Film> opt = filmRepository.findById(id);
        testFilm = opt.get();
    }

    @Then("A film with that ID is returned")
    public void a_film_with_that_id_is_returned() {
        Assertions.assertEquals(expected, testFilm, "Incorrect film fetched.");
    }
}
