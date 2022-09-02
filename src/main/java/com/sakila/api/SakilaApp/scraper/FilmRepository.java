package com.sakila.api.SakilaApp.scraper;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FilmRepository extends CrudRepository<Film, Integer> {
    @Query(nativeQuery = true, value = "SELECT category.category_id, category.name FROM film f INNER JOIN film_category ON f.film_id = film_category.film_id INNER JOIN category ON film_category.category_id = category.category_id where f.film_id = :id")
    Object getCategory(@Param("id") int id);

//    @Query(nativeQuery = true, value = "SELECT category.name FROM film INNER JOIN film_category ON film.film_id = film_category.film_id INNER JOIN category ON film_category.category_id = category.category_id WHERE film.film_id = :id");
//    Optional<Film> getFilmCategory(@Param("id") int id);
}
