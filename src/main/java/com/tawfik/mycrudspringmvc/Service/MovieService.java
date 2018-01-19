/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.Service;

import com.tawfik.mycrudspringmvc.model.Movie;
import com.tawfik.mycrudspringmvc.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tawfik
 */

public interface MovieService {

    Movie addToFavourite(Movie movie);
    
     public Movie getMovie(int id);
    
    List<Movie> getMoviesForUser(int id);
    
    void deleteUserMovie(User user,int movie_id);
    
    boolean isFavourite(User user,int movie_id);

}
