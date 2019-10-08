package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.order.OrderPosition;
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



}
