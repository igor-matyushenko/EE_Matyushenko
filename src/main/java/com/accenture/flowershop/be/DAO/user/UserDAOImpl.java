package com.accenture.flowershop.be.DAO.user;


import com.accenture.flowershop.be.entity.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
@Transactional(readOnly = true)
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);


    @Override
    public User findUserByLogin(String login) {
        LOG.debug("findUserByLogin "+ login);
        return em.find(User.class, login);
    }

    @Override
    public List<User> getUserList() {
        LOG.debug("getUserList");
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        LOG.debug("getUserById");
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        LOG.debug("addUser");
        em.persist(user);
        em.flush();
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        LOG.debug("deleteUser");
        em.remove(user);
        em.flush();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        LOG.debug("updateUser" + user.toString());
        em.merge(user);
        em.flush();
    }
}
