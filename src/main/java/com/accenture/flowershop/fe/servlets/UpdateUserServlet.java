package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.dto.UserLazyDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "updateUserServlet", urlPatterns = "/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private Mapper mapper;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            request.getRequestDispatcher("indexServlet").forward(request, response);
        } else {
            UserDTO userAdmin = (UserDTO) session.getAttribute("user");
            UserLazyDTO newUser = updateUserServlet(request);
//            User userEntity = mapper.map(newUser, User.class);
            userBusinessService.updateUser(newUser);
            session.setAttribute("user", userAdmin);
            session.setAttribute("userListAdmin", userBusinessService.getAllUsersForLazy());
            request.setAttribute("updateMessage", " is update! ");
            request.getRequestDispatcher("/WEB-INF/lib/updateUser.jsp").forward(request, response);
        }
    }

    private UserLazyDTO updateUserServlet(HttpServletRequest request) {
        UserLazyDTO userDTO = new UserLazyDTO();
        userDTO.setLogin(request.getParameter("login"));
        userDTO.setPassword(request.getParameter("password"));
        userDTO.setFirstName(request.getParameter("firstName"));
        userDTO.setLastName(request.getParameter("lastName"));
        userDTO.setAddress(request.getParameter("address"));
        userDTO.setEmail(request.getParameter("email"));
        userDTO.setPhoneNumber(request.getParameter("phoneNumber"));
        userDTO.setDiscount(Integer.parseInt(request.getParameter("discount")));
        userDTO.setBalance(BigDecimal.valueOf(Double.parseDouble(request.getParameter("balance"))));
        return userDTO;
    }

}
