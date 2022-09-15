package com.sakila.api.SakilaApp;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class SakilaAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/home/*").allowedOrigins("http://localhost:3000");
			}
		};
	}

	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private RatingRepository ratingRepository;
//	@Autowired
//	private EntityManager entityManager;

	public SakilaAppApplication(ActorRepository actorRepository, FilmRepository filmRepository, RatingRepository ratingRepository){
		this.actorRepository = actorRepository;
		this.filmRepository = filmRepository;
		this.ratingRepository = ratingRepository;
	}

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
		Optional<Film> f =  this.filmRepository.findById(id);
		return f;
	}

	@PostMapping("/addRating")
	public String addRating(
			@RequestBody Rating rating
	) {
		ratingRepository.save(rating);

		JsonObject j = new JsonObject();

		j.addProperty("message", "Rating added");

		return j.toString();
	}

	@GetMapping("/filmStats/{id}")
	public String getFilmStatsById(@PathVariable("id") int id) {

		JsonObject j = new JsonObject();

		try {
			Optional<Object> f = this.filmRepository.findFilmStatsById(id);
			Object[] fi = (Object[]) f.get();

			j.addProperty("rented", true);

			String[] options = new String[]{"id", "title", "description", "date", "length", "rating", "rentalRate", "genre", "timesRented", "timeWatched", "revenue"};
			for (int i = 0; i < ((Object[]) f.get()).length; i++) {
				j.addProperty(options[i], fi[i].toString());
			}
		} catch (NullPointerException | NoSuchElementException e) {
			// Null pointer is thrown when there are no relations in the rentals select statement
			// NoSuchElement is thrown when f.get is empty.

			j.addProperty("rented", false);

			Optional<Object> f = this.filmRepository.findFilmStatsUnrentedById(id);
			// Nothing can be shown if this value is empty, error is thrown to front-end.
			Object[] fi = (Object[]) f.get();

			String[] options = new String[]{"id", "title", "description", "date", "length", "rating", "rentalRate", "genre"};
			for (int i = 0; i < ((Object[]) f.get()).length; i++) {
				j.addProperty(options[i], fi[i].toString());
			}
		}

		Optional<Object> r = this.ratingRepository.findFilmReactionsById(id);
		Object[] ri = (Object[]) r.get();
		String[] reactionOptions = new String[]{ "wow", "xd", "love", "scary"};
		for (int i = 0; i < ((Object[]) r.get()).length; i++) {
			j.addProperty(reactionOptions[i], ri[i].toString());
		}

//		Gson resp = new Gson();
//		GsonBuilder b = resp.newBuilder();
//		b.create();

//		FilmSchema fs = new FilmSchema();
//		fs.setId();
		return j.toString();
	}

	@GetMapping("/filmStats")
	public @ResponseBody Iterable<Object> getFilmStats() {
		return filmRepository.getFilmStats();
	}




}
