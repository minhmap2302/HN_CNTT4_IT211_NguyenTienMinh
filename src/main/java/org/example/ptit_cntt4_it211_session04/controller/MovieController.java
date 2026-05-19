package org.example.ptit_cntt4_it211_session04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    static class Movie {

        private String movieId;
        private String title;
        private String genre;
        private double rating;

        public Movie(String movieId, String title, String genre, double rating) {
            this.movieId = movieId;
            this.title = title;
            this.genre = genre;
            this.rating = rating;
        }

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }

    private List<Movie> getSampleMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("M001", "Inception", "Sci-Fi", 8.8));
        movies.add(new Movie("M002", "Parasite", "Drama", 8.6));
        movies.add(new Movie("M003", "Interstellar", "Sci-Fi", 8.7));
        movies.add(new Movie("M004", "The Dark Knight", "Action", 9.0));

        return movies;
    }

    @GetMapping
    public List<Movie> getMoviesByGenre(@RequestParam(required = false) String genre) {
        List<Movie> movies = getSampleMovies();

        if (genre == null || genre.isEmpty()) {
            return movies;
        }

        List<Movie> result = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                result.add(movie);
            }
        }

        return result;
    }

    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable String movieId) {
        List<Movie> movies = getSampleMovies();

        for (Movie movie : movies) {
            if (movie.getMovieId().equalsIgnoreCase(movieId)) {
                return movie;
            }
        }

        return null;
    }
}