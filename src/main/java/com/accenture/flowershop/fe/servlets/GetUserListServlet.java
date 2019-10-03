package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.UserDTO;
import com.accenture.flowershop.fe.enums.Roles;
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
import java.util.List;

@WebServlet( name = "getUserListServlet",urlPatterns = "/getUserListServlet")
public class GetUserListServlet extends HttpServlet {
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
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user.getRole().equals(Roles.ADMIN)) {
                session.setAttribute("user", user);
                session.setAttribute("userListAdmin", mapper.map(userBusinessService.getAllUsersForLazy(),List.class));
                request.getRequestDispatcher("/WEB-INF/lib/updateUser.jsp").forward(request, response);
            }
        }
    }
}
