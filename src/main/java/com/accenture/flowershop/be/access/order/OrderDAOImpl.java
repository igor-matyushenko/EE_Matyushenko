package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(OrderDAOImpl.class);


    @Override
    @Transactional
    public void addOrder(Order order) {
        em.persist(order);
    }

    @Override
    @Transactional
    public Order getOrderById(Long idOrder) {
        LOG.debug("getOrderById: " + idOrder);
        return em.find(Order.class, idOrder);
    }

    @Override
    public List<Order> getAllOrders() {
        try {
            TypedQuery<Order> query =
                    em.createQuery(" from Order o ", Order.class);
            LOG.debug("getAllOrder: " + query.getResultList().toString());
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("List Order  : не найден");
            return null;
        }
    }

    @Override
    @Transactional
    public List<Order> getOrderByUser(String userLogin) {
        try {
            TypedQuery<Order> query =
                    em.createQuery("from Order o where o.userLogin=:userLogin", Order.class);
            query.setParameter("userLogin", userLogin);
            LOG.debug("getOrderByUserID: " + userLogin);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getOrderByUserID: " + userLogin + " не найден");
            return null;
        }
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        LOG.debug("updateOrder: " + order);
        em.merge(order);
    }
}
