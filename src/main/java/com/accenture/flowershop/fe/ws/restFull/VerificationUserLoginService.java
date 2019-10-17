package com.accenture.flowershop.fe.ws.restFull;


import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.dto.UserWsDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@Component
@Path("/v")
public class VerificationUserLoginService {

    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private Mapper mapper;

    private static final String SUCCESS_RESULT = "<result>success</result>";
    private static final String FAILURE_RESULT = "failure";


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
    @Path("/usersDto/{id}")
    @Produces("application/json")
    public UserWsDTO getUserDto(@PathParam("id") Long id) {
        User userEntity = userBusinessService.getUser(id);
        if (userEntity == null) return null;
        return mapper.map(userEntity, UserWsDTO.class);
    }

    @POST
    @Path("/promo")
    @Consumes(value={"text/xml", "application/json"})
    @Produces("text/html")
    public String addPromoActionForUser(UserWsDTO user) {
        User userEntity = userBusinessService.getUser(user.getId());
        if(userEntity != null){
            userEntity.setBalance(userEntity.getBalance().add(user.getBalance()));
            userBusinessService.updateUser(userEntity);
            return String.valueOf(Response.Status.OK);
        }
        return FAILURE_RESULT;
    }
}
