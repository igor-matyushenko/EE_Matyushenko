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


@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    @Transactional
    public void saveUser(User user) {
        log.debug("saveUser:"+user);
        em.persist(user);
        em.flush();
    }

    @Override
    @Transactional
    public void editUser(User user) {
        log.debug("updateUser");
        em.merge(user);
        em.flush();

    }

    @Override
    public User findUserByLogin(String login) {

        try {
            TypedQuery<User> query =
                    em.createQuery("select e from User e where e.login =:LOGIN" , User.class);
            query.setParameter("LOGIN", login);
            log.debug("findUserByLogin: " + login);
            return query.getSingleResult();
        } catch (NoResultException e) {
//            e.printStackTrace();
            log.debug("findUserByLogin: " + login + " не найден");
            return null;
        }
    }

    @Override
    public User findUserById(Long idUser) {
        return em.find(User.class,idUser);
    }


}
