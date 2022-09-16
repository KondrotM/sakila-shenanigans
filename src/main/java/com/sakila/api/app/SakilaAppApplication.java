package com.sakila.api.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.google.gson.JsonObject;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin("kondrot.net")
public class SakilaAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private RatingRepository ratingRepository;

	public SakilaAppApplication(ActorRepository actorRepository, FilmRepository filmRepository, RatingRepository ratingRepository){
		this.actorRepository = actorRepository;
		this.filmRepository = filmRepository;
		this.ratingRepository = ratingRepository;
	}

	/**
	 * CRUD get mappings
	 */
	@GetMapping("/allActors")
	public @ResponseBody
	Iterable<Actor> getAllActors(){
		return actorRepository.findAll();
	}

	@GetMapping("/actor/{id}")
	public Optional<Actor> getActorById(@PathVariable("id") int id) {
		return actorRepository.findById(id);
	}

	@GetMapping("/categories/{id}")
	public Object getFilmCategory(@PathVariable("id") int id) {
		return this.filmRepository.getCategory(id);
	}

	@GetMapping("/film")
	public @ResponseBody Iterable<Film> getAllFilms() {
		return filmRepository.findAll();
	}

	@GetMapping("/film/{id}")
	public Optional<Film> getFilmById(@PathVariable("id") int id){
		return this.filmRepository.findById(id);
	}

	/**
	 * Custom get mapping to get consolidated film data.
	 * Gets regular film data and rental data.
	 * Sometimes there is no rental data, in which case it just fetches film data.
	 * Finally, a count of film ratings is fetched.
	 * @param id id of film to get
	 * @return Json text which has been converted to a string
	 */
	@GetMapping("/filmStats/{id}")
	public String getFilmStatsById(@PathVariable("id") int id) {

		// Initialise object to return
		JsonObject j = new JsonObject();

		try {
			Optional<Object> f = this.filmRepository.findFilmStatsById(id);
			if (f.isPresent()) {
				Object[] fi = (Object[]) f.get();

				j.addProperty("rented", true);

				String[] options = new String[]{"id", "title", "description", "date", "length", "rating", "rentalRate", "genre", "timesRented", "timeWatched", "revenue"};
				for (int i = 0; i < ((Object[]) f.get()).length; i++) {
					j.addProperty(options[i], fi[i].toString());
				}
			} else {
				throw new NoSuchElementException();
			}
		} catch (NullPointerException | NoSuchElementException e) {
			// Null pointer is thrown when there are no relations in the rentals select statement
			// NoSuchElement is thrown when f.get is empty.

			j.addProperty("rented", false);

			Optional<Object> f = this.filmRepository.findFilmStatsUnrentedById(id);
			// Nothing can be shown if this value is empty, empty json is thrown to front-end.
			if (f.isPresent()) {
				Object[] fi = (Object[]) f.get();

				String[] options = new String[]{"id", "title", "description", "date", "length", "rating", "rentalRate", "genre"};
				for (int i = 0; i < ((Object[]) f.get()).length; i++) {
					j.addProperty(options[i], fi[i].toString());
				}
			}
		}

		Optional<Object> r = this.ratingRepository.findFilmReactionsById(id);
		if (r.isPresent()) {
			Object[] ri = (Object[]) r.get();
			String[] reactionOptions = new String[]{"wow", "xd", "love", "scary"};
			for (int i = 0; i < ((Object[]) r.get()).length; i++) {
				j.addProperty(reactionOptions[i], ri[i].toString());
			}
		}

		return j.toString();
	}

	/**
	 * Adds user emotion rating to the database.
	 * @param rating Rating which contains filmId and ratingId
	 * @return confirmation
	 */
	@PostMapping("/addRating")
	public String addRating(
			@RequestBody RatingDTO rating
	) {
		// Re-mapping rating to avoid critical security issue exclaimed in SonarCloud
		Rating persistentRating = new Rating();
		persistentRating.setRatingId(rating.getRatingIdDto());
		persistentRating.setFilmId(rating.getFilmIdDto());
		persistentRating.setUserId(rating.getUserIdDto());

		ratingRepository.save(persistentRating);

		JsonObject j = new JsonObject();

		j.addProperty("message", "Rating added");

		return j.toString();
	}


	/**
	 * Gets an iterable custom mapping of films. Implementation unfinished.
	 * @return List of lists of films
	 */
	@GetMapping("/filmStats")
	public @ResponseBody Iterable<Object> getFilmStats() {
		return filmRepository.getFilmStats();
	}




}
