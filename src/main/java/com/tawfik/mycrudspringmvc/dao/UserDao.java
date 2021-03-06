/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.dao;

import com.tawfik.mycrudspringmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tawfik
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);

}
