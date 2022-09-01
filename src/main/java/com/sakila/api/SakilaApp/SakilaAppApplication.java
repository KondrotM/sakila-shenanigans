package com.sakila.api.SakilaApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class SakilaAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}
	@Autowired
	private ActorRepository actorRepository;

	public SakilaAppApplication(ActorRepository actorRepository){
		this.actorRepository = actorRepository;

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





}
