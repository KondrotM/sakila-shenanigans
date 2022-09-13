package com.sakila.api.SakilaApp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
    @Query(nativeQuery = true, value =
            "select \n" +
                "coalesce(sum(case when rating_id = 0 and film_id = :id then 1 else 0 end), 0) as wow_count, \n" +
                "coalesce(sum(case when rating_id = 1 and film_id = :id then 1 else 0 end), 0) as xd_count,\n" +
                "coalesce(sum(case when rating_id = 2 and film_id = :id then 1 else 0 end), 0) as love_count,\n" +
                "coalesce(sum(case when rating_id = 3 and film_id = :id then 1 else 0 end), 0) as scary_count  from film_rating where film_id = :id")
    Optional<Object> findFilmReactionsById(@Param("id") int id);
}
