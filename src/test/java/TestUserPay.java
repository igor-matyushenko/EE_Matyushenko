import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessServiceImpl;
import com.accenture.flowershop.be.entity.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

public class TestUserPay {

    private static User user;
    private static BigDecimal orderPrice;
    private static BigDecimal testSum ;
    private static UserBusinessService userBusinessService;
    @BeforeClass
    public static void setUp(){
        userBusinessService = new UserBusinessServiceImpl();
        user = new User();
    }
    @Before
    public void init(){
        user.setBalance(BigDecimal.valueOf(2000));
        orderPrice = BigDecimal.valueOf(1333);
        testSum = user.getBalance().subtract(orderPrice);
    }

    @Test
    public void testValidPay(){
        User u = userBusinessService.pay(user,orderPrice);
        Assert.assertEquals(u.getBalance(), testSum);
        System.out.println( u.getBalance() + " : " + testSum);
    }

    @Test
    public void testInvalidPay(){
        User u2 = userBusinessService.pay(user,orderPrice);
        testSum = BigDecimal.valueOf(2000);
        Assert.assertNotEquals(u2.getBalance(),testSum);
        System.out.println( u2.getBalance() + " : " +testSum );
    }
}
