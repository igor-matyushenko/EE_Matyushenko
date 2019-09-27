package com.accenture.flowershop.be.access.order;

import com.accenture.flowershop.be.entity.Order.Basket;
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
public class BasketDAOImpl implements BasketDAO {

    @PersistenceContext
    private EntityManager em;

    private static final Logger LOG = LoggerFactory.getLogger(BasketDAOImpl.class);

    @Override
    public List<Basket> getBasketByUserId(Long idUser) {
        try {
            TypedQuery<Basket> query =
                    em.createQuery("from Basket e where e.orderID IS NULL AND e.userID =:idUser", Basket.class);
            query.setParameter("idUser", idUser);
            LOG.debug("getOrderByUserID: " + idUser);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getOrderByUserID: " + idUser + " не найден");
            return null;
        }
    }

    @Override
    public List<Basket> getActualBasketByUserId(Long idUser) {
        try {
            TypedQuery<Basket> query =
                    em.createQuery("from Basket e  " +
                            "where e.orderID IS NULL and e.userID =:idUser", Basket.class);
            query.setParameter("idUser", idUser);
            LOG.debug("getOrderByUserID: " + idUser);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getOrderByUserID: " + idUser + " не найден");
            return null;
        }
    }

    @Override
    public Basket getBasketByFlowerName(String flowerName) {
        try {
            TypedQuery<Basket> query =
                    em.createQuery("from Basket e " +
                            "where e.orderID IS NULL and e.flowerName =:flowerName", Basket.class);
            query.setParameter("flowerName", flowerName);
            LOG.debug("getOrderByUserID: " + flowerName);
            return query.getSingleResult();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.debug("getBasket: " + flowerName + " не найден");
            return null;
        }
    }

    @Override
    public boolean addBaskets(Basket basket) {
        LOG.debug("addBasket:  " + basket.toString());
        em.persist(basket);
        return true;
    }

    @Override
    public List<Basket> getBasketByOrderId(Long orderID) {
        try {
            TypedQuery<Basket> query =
                    em.createQuery("select e from Basket e where e.order_id =:orderID", Basket.class);
            query.setParameter("orderID", orderID);
            LOG.debug("getBasketByOrderId: " + orderID);
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            LOG.warn("getOrderByUserID: " + orderID + " не найден");
            return null;
        }
    }

    @Override
    public boolean updateBasket(Basket basket) {
        LOG.debug("updateBasket: " + basket);
        em.merge(basket);
        return true;
    }

    @Override
    public void updateBasketsList(List<Basket> basketList) {
        for (Basket basket : basketList) {
            LOG.debug("updateBasket: " + basket);
            em.merge(basket);
        }
    }


}
