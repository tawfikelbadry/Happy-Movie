/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.model;

import java.io.Serializable;
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
import org.hibernate.annotations.Type;

/**
 *
 * @author tawfik
 */
@Entity
public class Movie implements Serializable {

    @Id
    private int id;
    private String title;
    private int vote_count;
    private double vote_average;
    private String poster_path;
    private String original_title;

    @Type(type = "text")
    private String overview;
    private String release_date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_movies", joinColumns = {
        @JoinColumn(name = "movie_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "user_id",
                        nullable = false)})
    private List<User> users=new ArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    
    

    @Override
    public String toString() {
        return "Movie{\n" + "id=" + id + ", title=" + title + ",\n vote_count=" + vote_count + ", vote_average=" + vote_average + ",\n poster_path=" + poster_path + ", original_title=" + original_title + "\n, overview=" + overview + ", release_date=" + release_date + '\n' + '}';
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
