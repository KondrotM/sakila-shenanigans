package com.sakila.api.app;

import javax.persistence.*;

@Entity
@Table(name="film")
public class Film {
    // Attributes
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "release_year")
    int released;

    @Column(name = "rating")
    String rating;

    @Column(name = "length")
    int length;

    @Basic(optional = true)
    @Column(name = "rental_rate", nullable = true)
    double rentalRate;

    /**
     * number of times watched
     * total time watched
     * revenue
     */

    public Film(int id, String title, String description, int released, String rating, int length) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.released = released;
        this.rating = rating;
        this.length = length;
    }

    public Film(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Film(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }
}
