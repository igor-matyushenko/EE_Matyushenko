package com.accenture.flowershop.be.business.order;

import com.accenture.flowershop.be.access.order.OrderPositionDAO;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.Order.OrderPosition;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class OrderPositionBusinessServiceImpl implements OrderPositionBusinessService {

    @Autowired
    private OrderPositionDAO orderPositionDAO;

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private UserBusinessService userBusinessService;

    private static final Logger log = LoggerFactory.getLogger(OrderPositionBusinessServiceImpl.class);
    private BigDecimal totalSumBasket;


    @Override
    public List<OrderPosition> getOrderPositionByOrderId(Long idOrder) {
        return orderPositionDAO.getOrderPositionByOrderId(idOrder);
    }

    @Override
    public void updateOrderPositionList(List<OrderPosition> orderPositionList) {
        orderPositionDAO.updateOrderPositionList(orderPositionList);
    }


    @Override
    public List<OrderPosition> getOrderPositionByUserId(Long idUser) {
        return orderPositionDAO.getOrderPositionByUserId(idUser);
    }

    @Override
    @Transactional
    public List<OrderPosition> getNewOrderPositionByUserId(Long idUser) {
        return orderPositionDAO.getActualOrderPositionByUserId(idUser);
    }

    @Override
    public Boolean addOrderPosition(String userLogin, Long flowerID, Long quantityToOrderPosition, Long quantityFlower) {
        if (quantityToOrderPosition <= 0 || quantityToOrderPosition > quantityFlower) {
            return false;
        }
        User user = userBusinessService.findUserByLogin(userLogin);
        Flower flower = flowerBusinessService.getFlowerById(flowerID);
        BigDecimal sumWithDiscountUser = userBusinessService.userSumDiscount(flower.getPriceFlower(), user.getDiscount(), quantityToOrderPosition);
        OrderPosition orderPosition = new OrderPosition(user.getId(), flower.getId(), flower.getTitleFlower(), quantityToOrderPosition, sumWithDiscountUser);
        return addToBasketWithCheckExist(orderPosition, flower);
    }

    private boolean addToBasketWithCheckExist(OrderPosition orderPosition, Flower flower) {
        OrderPosition orderPositionByFlowerName = orderPositionDAO.getOrderPositionByFlowerName(orderPosition.getFlowerName());
        if (orderPositionByFlowerName == null) {
            if (totalSumBasket == null) totalSumBasket = new BigDecimal(0);
            totalSumBasket = totalSumBasket.add(orderPosition.getTotalPrice());
            orderPositionDAO.addOrderPosition(orderPosition);
            return changeQuantityFlower(orderPosition, flower);
        }
        orderPositionByFlowerName.setTotalPrice(orderPositionByFlowerName.getTotalPrice().add(orderPosition.getTotalPrice()));
        orderPositionByFlowerName.setQuantity(orderPositionByFlowerName.getQuantity() + orderPosition.getQuantity());
        orderPositionDAO.updateOrderPosition(orderPositionByFlowerName);
        totalSumBasket = totalSumBasket.add(orderPositionByFlowerName.getTotalPrice());
        return changeQuantityFlower(orderPositionByFlowerName, flower);
    }

    private boolean changeQuantityFlower(OrderPosition orderPosition, Flower flower) {
        flower.setQuantity(flower.getQuantity() - orderPosition.getQuantity());
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
