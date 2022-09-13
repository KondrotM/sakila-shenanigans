package com.sakila.api.SakilaApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@SpringBootTest

class MockitoTests {

	private SakilaAppApplication sakilaAppApplication;
	@Mock
	private FilmRepository filmRepository;

	@Mock
	private ActorRepository actorRepository;

	@Mock
	private RatingRepository ratingRepository;
	@BeforeEach
	void setup(){
		filmRepository = mock(FilmRepository.class);
		actorRepository = mock(ActorRepository.class);
		ratingRepository = mock(RatingRepository.class);
		sakilaAppApplication = new SakilaAppApplication(actorRepository, filmRepository, ratingRepository);
	}

	@Test
	void testGetAllFilms() {
		// Create list
		List<Film> filmList = new ArrayList<>();

		Film testFilm = new Film();
		Film testFilm2 = new Film();

		filmList.add(testFilm);
		filmList.add(testFilm2);

		// Creates iterable from the list
		Iterable <Film> filmIterable = filmList;

		// Defines behaviour of mock repos method
		when(filmRepository.findAll()).thenReturn(filmIterable);

		// Sets my expected and actual variables
		Iterable <Film> Expected = filmIterable;
		Iterable <Film> Actual = sakilaAppApplication.getAllFilms();

		Assertions.assertEquals(Expected, Actual, "Get all films incorrect value returned.");


	}


	/** no
	@Test
	void contextLoads() {

	}
	*/

}
