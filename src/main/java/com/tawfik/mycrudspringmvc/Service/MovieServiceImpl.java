/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.Service;

import com.tawfik.mycrudspringmvc.dao.MovieDao;
import com.tawfik.mycrudspringmvc.dao.UserDao;
import com.tawfik.mycrudspringmvc.model.Movie;
import com.tawfik.mycrudspringmvc.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tawfik
 */
@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDao movieDao;

    @Autowired
    UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Movie addToFavourite(Movie movie) {
        return movieDao.save(movie);
    }

    @Override
    public Movie getMovie(int id) {
        return movieDao.findOne(id);
    }

    @Override
    public List<Movie> getMoviesForUser(int id) {
        User user = userDao.findOne(id);
        return user.getMovies();
    }

    @Transactional
    @Override
    public void deleteUserMovie(User user, int movie_id) {
        List<Movie> userMovies = new ArrayList<>();
        for (int i = 0; i < user.getMovies().size(); i++) {
            if (user.getMovies().get(i).getId() != movie_id) {
                userMovies.add(user.getMovies().get(i));
            }
        }
        user.setMovies(userMovies);
        userDao.save(user);
    }

    @Override
    public boolean isFavourite(User user, int movie_id) {
        List<Movie> movies = user.getMovies();
        System.out.println("size = "+movies.size());
        for (Movie movie : movies) {
            if (movie.getId() == movie_id) {
                return true;
            }
        }
        return false;
    }

}
