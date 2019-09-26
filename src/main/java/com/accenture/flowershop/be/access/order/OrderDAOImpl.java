package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.access.user.UserDAOImpl;
import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.Order.Order;
import com.accenture.flowershop.be.entity.user.User;
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
public class OrderDAOImpl implements OrderDAO{
    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(OrderDAOImpl.class);


    @Override
    public void addOrder(Order order) {
        em.persist(order);
        em.flush();
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return em.find(Order.class,idOrder);
    }

    @Override
    public List<Order> getAllOrder() {
        try {
            TypedQuery<Order> query =
                    em.createQuery(" from Order e " , Order.class);
            log.debug("getAllOrder: " );
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            log.debug("List Order  : " + " не найден");
            return null;
        }
    }

    @Override
    public List<Order> getOrderByUserID(Long userID) {
        try {
            TypedQuery<Order> query =
                    em.createQuery("from Order o where o.user.id=:userID", Order.class);
            query.setParameter("userID", userID);
            log.debug("getOrderByUserID: " + userID);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            log.debug("getOrderByUserID: " + userID + " не найден");
            return null;
        }
    }

    @Override
    public void editOrder(Order order) {
        em.merge(order);
    }
}
