package com.accenture.flowershop.fe.ws.rest;


import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserWsDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Component
@Path("/v")
public class VerificationUserLoginService {

    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private Mapper mapper;

    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "<result>failure</result>";


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("verify/{login}")
    public Boolean verify(@PathParam("login") String login) {
        if (userBusinessService.findUserByLogin(login) != null) {
            return true;
        }
        return false;
    }

    @GET
    @Path("/users/{userLogin}")
    @Produces("application/json")
    public User getUser(@PathParam("userLogin") String userLogin) {
        return userBusinessService.getUser(userLogin);
    }

    @GET
    @Path("/usersDto/{userLogin}")
    @Produces("application/json")
    public UserWsDTO getUserDto(@PathParam("userLogin") String userLogin) {
        User userEntity = userBusinessService.getUser(userLogin);
        if (userEntity == null) return null;
        return mapper.map(userEntity, UserWsDTO.class);
    }

    @POST
    @Path("/promo")
    @Consumes("application/json")
    @Produces("html/text")
    public String addPromoActionForUser(User user) {
        user.setBalance(user.getBalance().add(new BigDecimal(999)));
        if (userBusinessService.updateUser(user)) {
            return SUCCESS_RESULT;
        }
        return FAILURE_RESULT;
    }
}
