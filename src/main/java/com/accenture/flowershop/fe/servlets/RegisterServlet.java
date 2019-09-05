package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.UserRepositoryImpl;
import com.accenture.flowershop.be.business.UsersRepository;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(name = "RegisterServlet", urlPatterns = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        this.usersRepository = new UserRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");



        if (usersRepository.createNewUser(login,password)) {
            req.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
        } else {
            req.setAttribute("error","This login have in system!\n"+
                    "Please choose other login");
            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
        }
    }


}

