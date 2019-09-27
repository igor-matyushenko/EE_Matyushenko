package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public void saveUser(User user) {
        LOG.debug("saveUser: " + user);
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        LOG.debug("updateUser: " + user);
        em.merge(user);
    }

    @Override
    public User findUserByLogin(String login) {
        try {
            TypedQuery<User> query =
                    em.createQuery("select e from User e where e.login =:LOGIN", User.class);
            query.setParameter("LOGIN", login);
            LOG.debug("findUserByLogin: " + login);
            return query.getSingleResult();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("findUserByLogin: " + login + " не найден");
            return null;
        }
    }

    @Override
    public User findUserById(Long idUser) {
        LOG.debug("findUserById: " + idUser);
        return em.find(User.class, idUser);
    }

}
