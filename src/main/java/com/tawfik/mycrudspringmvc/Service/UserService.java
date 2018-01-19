/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.Service;

import com.tawfik.mycrudspringmvc.model.User;

/**
 *
 * @author tawfik
 */
public interface UserService {

    void save(User user);

    boolean isUser(User user);

    User getUser(String username);

    User getUser(int id);

}
