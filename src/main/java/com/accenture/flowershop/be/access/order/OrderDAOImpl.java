package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(OrderDAOImpl.class);


    @Override
    public void addOrder(Order order) {
        em.persist(order);
    }

    @Override
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
    public List<Order> getOrderByUserID(Long userID) {
        try {
            TypedQuery<Order> query =
                    em.createQuery("from Order o where o.user.id=:userID", Order.class);
            query.setParameter("userID", userID);
            LOG.debug("getOrderByUserID: " + userID);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getOrderByUserID: " + userID + " не найден");
            return null;
        }
    }

    @Override
    public void updateOrder(Order order) {
        LOG.debug("updateOrder: " + order);
        em.merge(order);
    }
}
