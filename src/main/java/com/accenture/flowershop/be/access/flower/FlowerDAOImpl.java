package com.accenture.flowershop.be.access.flower;

import com.accenture.flowershop.be.access.user.UserDAOImpl;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FlowerDAOImpl implements FlowerDAO {
    @PersistenceContext
    private EntityManager em;

    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public List<Flower> getAllFlower() {
        try {
            TypedQuery<Flower> query =
                    em.createQuery("from Flower f " , Flower.class);
            log.debug("getAllFlower: " );
            return query.getResultList();
        } catch (NoResultException e) {
//            e.printStackTrace();
            log.debug("List flower  : " + " не найден");
            return null;
        }
    }

    @Override
    @Transactional
    public void addFlower(Flower flower) {
        log.debug("addFlower:"+flower);
        em.persist(flower);
        em.flush();
    }

    @Override
    @Transactional
    public void deleteFlower(Flower flower) {
        log.debug("deleteFlower:"+flower);
        em.remove(flower);
    }

    @Override
    @Transactional
    public void editFlower(Flower flower) {
        log.debug("editFlower:"+flower);
        em.merge(flower);
        em.flush();
    }

    @Override
    @Transactional
    public Flower getFlowerById(Long idFlower) {
        return em.find(Flower.class,idFlower);
    }
}
