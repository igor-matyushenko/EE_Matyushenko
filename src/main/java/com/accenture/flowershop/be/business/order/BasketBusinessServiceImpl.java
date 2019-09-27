package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.BasketDAO;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.Order.Basket;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class BasketBusinessServiceImpl implements BasketBusinessService {

    @Autowired
    private BasketDAO basketDAO;

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private UserBusinessService userBusinessService;

    private static final Logger log = LoggerFactory.getLogger(BasketBusinessServiceImpl.class);
    private BigDecimal totalSumBasket;


    @Override
    public List<Basket> getBasketByOrderId(Long idOrder) {
        return basketDAO.getBasketByOrderId(idOrder);
    }

    @Override
    public void updateBasketList(List<Basket> basketList) {
        basketDAO.updateBasketsList(basketList);
    }


    @Override
    public List<Basket> getBasketsByUserId(Long idUser) {
        return basketDAO.getBasketByUserId(idUser);
    }

    @Override
    public List<Basket> getNewBasketByUserId(Long idUser) {
        return basketDAO.getActualBasketByUserId(idUser);
    }

    @Override
    public Boolean addBasket(String userLogin, Long flowerID, Long quantityToBasket, Long quantityFlower) {
        if (quantityToBasket <= 0 || quantityToBasket > quantityFlower) {
            return false;
        }
        User user = userBusinessService.findUserByLogin(userLogin);
        Flower flower = flowerBusinessService.getFlowerById(flowerID);
        BigDecimal sumWithDiscountUser = userBusinessService.userSumDiscount(flower.getPriceFlower(), user.getDiscount(), quantityToBasket);
        Basket basket = new Basket(user.getId(), flower.getId(), flower.getTitleFlower(), quantityToBasket, sumWithDiscountUser);
        return addToBasketWithCheckExist(basket, flower);
    }

    private boolean addToBasketWithCheckExist(Basket basket, Flower flower) {
        Basket basketByFlowerName = basketDAO.getBasketByFlowerName(basket.getFlowerName());
        if (basketByFlowerName == null) {
            if (totalSumBasket == null) totalSumBasket = new BigDecimal(0);
            totalSumBasket = totalSumBasket.add(basket.getTotalPrice());
            basketDAO.addBaskets(basket);
            return changeQuantityFlower(basket, flower);
        }
        basketByFlowerName.setTotalPrice(basketByFlowerName.getTotalPrice().add(basket.getTotalPrice()));
        basketByFlowerName.setQuantity(basketByFlowerName.getQuantity() + basket.getQuantity());
        basketDAO.updateBasket(basketByFlowerName);
        totalSumBasket = totalSumBasket.add(basketByFlowerName.getTotalPrice());
        return changeQuantityFlower(basketByFlowerName, flower);
    }

    private boolean changeQuantityFlower(Basket basket, Flower flower) {
        flower.setQuantity(flower.getQuantity() - basket.getQuantity());
        flowerBusinessService.updateFlower(flower);
        return true;
    }

    @Override
    public BigDecimal getTotalSumFromActualBasket() {
        return totalSumBasket.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public void setTotalSumFromActualBasket(BigDecimal totalSumBasket) {
        this.totalSumBasket = totalSumBasket;
    }
}
