-- General film statement
select film.film_id, film.description, film.release_year, film.length, film.rating, film.rental_rate,
	category.name as category, count(rental_id) as times_rented, count(rental_id)*film.length as minutes_watched, count(rental_id) * rental_rate as revenue
from film
inner join film_category
on film.film_id = film_category.film_id
inner join category
on film_category.category_id = category.category_id
inner join inventory
on inventory.film_id = film.film_id
inner join rental
on rental.inventory_id = inventory.inventory_id
-- where film.film_id = 1;
group by film.film_id
order by revenue desc;

select film.film_id, film.description, film.release_year, film.length, film.rating, film.rental_rate, category.name as category, count(rental_id) as times_rented, count(rental_id)*film.length as minutes_watched, count(rental_id) * rental_rate as revenue from film inner join film_category on film.film_id = film_category.film_id inner join category on film_category.category_id = category.category_id inner join inventory on inventory.film_id = film.film_id inner join rental on rental.inventory_id = inventory.inventory_id group by film.film_id order by film.name desc;

-- Top rented movies
select film.film_id, count(rental_id) as times_rented
from film
inner join inventory
on inventory.film_id = film.film_id
inner join rental
on rental.inventory_id = inventory.inventory_id
group by film.film_id
order by times_rented desc;