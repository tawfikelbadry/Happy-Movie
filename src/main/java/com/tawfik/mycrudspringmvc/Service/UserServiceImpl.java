/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.Service;

import com.tawfik.mycrudspringmvc.dao.UserDao;
import com.tawfik.mycrudspringmvc.model.User;
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
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);

    }

    @Transactional
    @Override
    public boolean isUser(User user) {
        List<User> users = em.createQuery("from User where username='" + user.getUsername() + "' and password='" + user.getPassword() + "'").getResultList();
        return users.size() > 0;
    }

    @Override
    public User getUser(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User getUser(int id) {
        return (User) em.createQuery("from User where id = " + id).getResultList().get(0);
    }

}
