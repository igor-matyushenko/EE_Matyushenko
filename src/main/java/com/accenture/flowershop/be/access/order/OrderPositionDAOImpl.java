package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order.OrderPosition;
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
public class OrderPositionDAOImpl implements OrderPositionDAO{

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(OrderPositionDAOImpl.class);

    @Override
    @Transactional
    public List<OrderPosition> getOrderPositionByUserId(Long idUser) {
        try {
            TypedQuery<OrderPosition> query =
                    em.createQuery("from OrderPosition e where e.order.id IS NULL AND e.userID =:idUser", OrderPosition.class);
            query.setParameter("idUser", idUser);
            LOG.debug("getOrderPositionByUserId: " + idUser);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getOrderPositionByUserId: " + idUser + " не найден");
            return null;
        }
    }

    @Override
    @Transactional
    public List<OrderPosition> getActualOrderPositionByUserId(Long idUser) {
        try {
            TypedQuery<OrderPosition> query =
                    em.createQuery("from OrderPosition e  " +
                            "where e.order.id IS NULL and e.userID =:idUser", OrderPosition.class);
            query.setParameter("idUser", idUser);
            LOG.debug("getActualOrderPositionByUserId : " + idUser);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getActualOrderPositionByUserId: " + idUser + " не найден");
            return null;
        }
    }

    @Override
    @Transactional
    public OrderPosition getOrderPositionByFlowerName(String flowerName) {
        try {
            TypedQuery<OrderPosition> query =
                    em.createQuery("from OrderPosition e " +
                            "where e.order.id IS NULL and e.flowerName =:flowerName", OrderPosition.class);
            query.setParameter("flowerName", flowerName);
            LOG.debug("getOrderPositionByFlowerName: " + flowerName);
            return query.getSingleResult();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.debug("getOrderPositionByFlowerName: " + flowerName + " не найден");
            return null;
        }
    }

    @Override
    @Transactional
    public boolean addOrderPosition(OrderPosition orderPosition) {
        LOG.debug("addOrderPosition:  " + orderPosition.toString());
        em.persist(orderPosition);
        return true;
    }

    @Override
    @Transactional
    public List<OrderPosition> getOrderPositionByOrderId(Long orderID) {
        try {
            TypedQuery<OrderPosition> query =
                    em.createQuery("select e from OrderPosition e where e.order_id =:orderID", OrderPosition.class);
            query.setParameter("orderID", orderID);
            LOG.debug("getOrderPositionByOrderId: " + orderID);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getOrderPositionByOrderId: " + orderID + " не найден");
            return null;
        }
    }

    @Override
    @Transactional
    public boolean updateOrderPosition(OrderPosition orderPosition) {
        LOG.debug("updateOrderPosition: " + orderPosition);
        em.merge(orderPosition);
        return true;
    }

    @Override
    @Transactional
    public void updateOrderPositionList(List<OrderPosition> orderPositionList) {
        for (OrderPosition orderPosition : orderPositionList) {
            LOG.debug("updateOrderPositionList: " + orderPosition);
            em.merge(orderPosition);
        }
    }


}
