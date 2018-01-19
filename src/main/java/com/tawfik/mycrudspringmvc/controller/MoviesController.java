/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.controller;

import com.tawfik.mycrudspringmvc.Service.MovieService;
import com.tawfik.mycrudspringmvc.Service.UserService;
import com.tawfik.mycrudspringmvc.jsonProcessing.JsonParser;
import com.tawfik.mycrudspringmvc.model.Movie;
import com.tawfik.mycrudspringmvc.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tawfik
 */
@Controller
public class MoviesController {

    @Autowired
    MovieService movieService;

    @Autowired
    UserService userService;

    private JsonParser parser = new JsonParser();

    @RequestMapping(value = "/{cat_type}", method = RequestMethod.GET)
    public String home(Model model, @PathVariable String cat_type, HttpSession session) {
        try {
            List<Movie> movies = null;
            if (cat_type.equals("favourites")) {
                movies = movieService.getMoviesForUser(((User) session.getAttribute("user")).getId());

            } else {
                movies = parser.getMovies(cat_type);
            }
            model.addAttribute("movies", movies);
            model.addAttribute("cat_type", cat_type);
        } catch (IOException ex) {

        }

        return "index";
    }

    @RequestMapping(value = "/{cat_type}/{id}", method = RequestMethod.GET)
    public String details(Model model, @PathVariable String cat_type, @PathVariable int id, HttpSession session) {
        try {
            Movie movie;
            if (cat_type.equals("favourites")) {
                movie = movieService.getMovie(id);
            } else {
                movie = parser.getMovie(id, cat_type);

            }

            User usr = (User) session.getAttribute("user");

            model.addAttribute("movie", movie);
            model.addAttribute("cat_type", cat_type);
            model.addAttribute("movie_id", id);
            model.addAttribute("trailers", parser.getEmbededMovieTrailer(id));
            model.addAttribute("is_fav", movieService.isFavourite(usr, id));

        } catch (IOException ex) {

        }

        return "movie-details";
    }

    @RequestMapping(value = "/{cat_type}/{id}", method = RequestMethod.POST)
    public String AddToFavourite(Model model, @PathVariable String cat_type, @PathVariable int id, HttpSession session) {
        try {
            Movie movie = parser.getMovie(id, cat_type);

            // add movie to database to favourite
            Movie mv = movieService.addToFavourite(movie);
            System.out.println("----------------------------------------");
            System.out.println(mv);
            User usr = (User) session.getAttribute("user");

            User user = userService.getUser(usr.getId());

            user.getMovies().add(mv);
            userService.save(user);

            System.out.println(user);
            System.out.println("----------------------------------------");

            model.addAttribute("movie", movie);
            model.addAttribute("cat_type", cat_type);
            model.addAttribute("movie_id", id);
            model.addAttribute("trailers", parser.getEmbededMovieTrailer(id));
            model.addAttribute("is_fav", movieService.isFavourite(user, id));

        } catch (IOException ex) {

        }

        return "movie-details";
    }

    @RequestMapping(value = "/{cat_type}/{id}/delete", method = RequestMethod.POST)
    public String removeFromFavourite(Model model, @PathVariable String cat_type, @PathVariable int id, HttpSession session) {
        try {
            Movie movie = parser.getMovie(id, cat_type);

            // add movie to database to favourite
            Movie mv = movieService.getMovie(movie.getId());
            User usr = (User) session.getAttribute("user");

            movieService.deleteUserMovie(usr, id);

            System.out.println("-------------deleted---------------------------");

            model.addAttribute("movie", movie);
            model.addAttribute("cat_type", cat_type);
            model.addAttribute("movie_id", id);
            model.addAttribute("trailers", parser.getEmbededMovieTrailer(id));

        } catch (IOException ex) {

        }

        return "redirect:/"+cat_type+"/"+id;
    }

}
