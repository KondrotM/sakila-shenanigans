package com.sakila.api.SakilaApp;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

//	@Autowired
//	private EntityManager entityManager;

	public SakilaAppApplication(ActorRepository actorRepository, FilmRepository filmRepository){
		this.actorRepository = actorRepository;
		this.filmRepository = filmRepository;
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

//		Film fi = f.get();

//		System.out.println(fi.getRental_rate());

		return f;

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
		} catch (NullPointerException e) {
			// Null pointer is thrown when there are no relations in the rentals select statement

			j.addProperty("rented", false);

			Optional<Object> f = this.filmRepository.findFilmStatsUnrentedById(id);
			Object[] fi = (Object[]) f.get();

			String[] options = new String[]{"id", "title", "description", "date", "length", "rating", "rentalRate", "genre"};
			for (int i = 0; i < ((Object[]) f.get()).length; i++) {
				j.addProperty(options[i], fi[i].toString());
			}
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
