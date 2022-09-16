package com.sakila.api.app.SystemTests;

import com.google.gson.JsonObject;
import com.sakila.api.app.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@SpringBootTest

class MockitoTests {

	private SakilaAppApplication sakilaAppApplication;
//	@Mock
	@Mock
	private FilmRepository filmRepository;

	@Mock
	private ActorRepository actorRepository;

	@Mock
	private RatingRepository ratingRepository;
	@BeforeEach
	void setup(){
		filmRepository = mock(FilmRepository.class);
//		filmRepository = new F;
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

	@Test
	void testGetFilmStatsById() {
		/*
		 * Branch 1
		 */
		Object[] film1;
		film1 = new Object[]{
			"1000",
			"ZORRO ARK",
			"A Intrepid Panorama of a Mad Scientist And a Boy who must Redeem a Boy in A Monastery",
			"2006-01-01",
			"50",
			"NC-17",
			"4.99",
			"Comedy",
			"31",
			"1550",
			"154.69"
		};

		JsonObject j = new JsonObject();
		j.addProperty("rented",true);
		j.addProperty("id","1000");
		j.addProperty("title","ZORRO ARK");
		j.addProperty("description","A Intrepid Panorama of a Mad Scientist And a Boy who must Redeem a Boy in A Monastery");
		j.addProperty("date","2006-01-01");
		j.addProperty("length","50");
		j.addProperty("rating","NC-17");
		j.addProperty("rentalRate","4.99");
		j.addProperty("genre","Comedy");
		j.addProperty("timesRented","31");
		j.addProperty("timeWatched","1550");
		j.addProperty("revenue","154.69");

		String expected = j.toString();

		when(filmRepository.findFilmStatsById(1000)).thenReturn(Optional.of(film1));
		String actual = sakilaAppApplication.getFilmStatsById(1000);

		Assertions.assertEquals(expected, actual, "Mismatch");

		/*
		 * Branch 2
		 */
		Object[] film2;
		film2 = new Object[]{
				"1001",
				"ZORRO ARK",
				"A Intrepid Panorama of a Mad Scientist And a Boy who must Redeem a Boy in A Monastery",
				"2006-01-01",
				"50",
				"NC-17",
				"4.99",
				"Comedy",
		};
		when(filmRepository.findFilmStatsUnrentedById(1001)).thenReturn(Optional.of(film2));

		Object[] ratings;
		ratings = new Object[]{
				"1", "2", "3", "4"
		};
		when(ratingRepository.findFilmReactionsById(1001)).thenReturn(Optional.of(ratings));

		j = new JsonObject();
		j.addProperty("rented",false);
		j.addProperty("id","1001");
		j.addProperty("title","ZORRO ARK");
		j.addProperty("description","A Intrepid Panorama of a Mad Scientist And a Boy who must Redeem a Boy in A Monastery");
		j.addProperty("date","2006-01-01");
		j.addProperty("length","50");
		j.addProperty("rating","NC-17");
		j.addProperty("rentalRate","4.99");
		j.addProperty("genre","Comedy");
		j.addProperty("wow","1");
		j.addProperty("xd","2");
		j.addProperty("love","3");
		j.addProperty("scary","4");

		expected = j.toString();
		actual = sakilaAppApplication.getFilmStatsById(1001);
		Assertions.assertEquals(expected, actual, "Mismatch");

		/*
		 * Branch 3
		 */

		j = new JsonObject();
		j.addProperty("rented",false);
		expected = j.toString();
		actual = sakilaAppApplication.getFilmStatsById(1002);
		Assertions.assertEquals(expected, actual, "Mismatch");
	}


	@Test
	void testAddRating() {
		RatingDTO r = new RatingDTO();
		r.setUserIdDto(1);
		r.setRatingIdDto(1);
		r.setFilmIdDto(1);

		JsonObject j = new JsonObject();
		j.addProperty("message", "Rating added");

		String expected = j.toString();
		String actual = sakilaAppApplication.addRating(r);

		Assertions.assertEquals(expected, actual, "Mismatch");


	}


}
