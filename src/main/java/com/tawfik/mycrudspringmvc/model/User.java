/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author tawfik
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 8, max = 30, message = "Full name must be between 8 and 30 letters")
    private String full_name;
    @Size(min = 5, message = "UserName Can't Be Less than 5 Letters")
    @NotEmpty(message = "username is required")
    private String username;
    @NotEmpty(message = "password is required")
    @NotNull
    private String password;
    @Email(message = "Not Valid Email")
    @NotEmpty(message = "Email is required")
    @NotNull
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_movies", joinColumns = {
        @JoinColumn(name = "user_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "movie_id",
                        nullable = false)})
    private List<Movie> movies=new ArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", full_name=" + full_name + ", username=" + username + ", password=" + password + ", email=" + email + ", movies=" + movies + '}';
    }

}
