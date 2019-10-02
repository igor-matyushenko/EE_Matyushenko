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

//    @Override
//    @Transactional
//    public List<OrderPosition> getOrderPositionByUserId(Long idUser, Long idOrder) {
//        try {
//            TypedQuery<OrderPosition> query =
//                    em.createQuery("from OrderPosition e where e.order.id=:idOrder AND e.userID =:idUser", OrderPosition.class);
//            query.setParameter("idUser", idUser);
//            query.setParameter("idOrder", idOrder);
//            LOG.debug("getOrderPositionByUserId: " + idUser + " idOrder: "+idOrder);
//            return query.getResultList();
//        } catch (NoResultException e) {
////            e.printStackTrace();
//            LOG.warn("getOrderPositionByUserId: " + + idUser + " idOrder: "+idOrder+ " not found");
//            return null;
//        }
//    }

    @Override
    @Transactional
    public List<OrderPosition> getOrderPositionByUser(Long userId) {
        try {
            TypedQuery<OrderPosition> query =
                    em.createQuery("from OrderPosition e  " +
                            "where  e.userId =:idUser", OrderPosition.class);
            query.setParameter("idUser", userId);
            LOG.debug("getActualOrderPositionByUserId : " + userId);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getActualOrderPositionByUserId: " + userId + " не найден");
            return null;
        }
    }

    @Override
    @Transactional
    public List<OrderPosition> getActualOrderPositionByUserId(Long idUser, Long idOrder) {
        try {
            TypedQuery<OrderPosition> query =
                    em.createQuery("from OrderPosition e  " +
                            "where e.orderId = :idOrder and e.userId =:idUser", OrderPosition.class);
            query.setParameter("idUser", idUser);
            query.setParameter("idOrder", idOrder);
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
