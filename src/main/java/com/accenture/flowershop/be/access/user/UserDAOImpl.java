package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.enums.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    @Transactional
    public void saveUser(User user) {
        LOG.debug("saveUser: " + user);
        em.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        LOG.debug("updateUser: " + user);
        em.merge(user);
    }

    @Override
    @Transactional
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
    @Transactional
    public List<User> getUserList() {
        try {
//            Roles role = Roles.USER;
            TypedQuery<User> query =
                    em.createQuery(
                            "from User u ", User.class);
//            query.setParameter("role",role);where u.role =:role
            LOG.debug("getUserList: " + query.getResultList());
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getUserList: не найден");
            return null; }
    }

    @Override
    @Transactional
    public User findUserById(Long idUser) {
        LOG.debug("findUserById: " + idUser);
        return em.find(User.class, idUser);
    }


}
