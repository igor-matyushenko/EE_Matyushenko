package com.accenture.flowershop.fe.ws.restFull;


import com.accenture.flowershop.fe.dto.UserWsDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.math.BigDecimal;

public class SpringRestClient {
    private static final String GET_VERIFY_ENDPOINT_URL = "http://localhost:8080/flowershop/rest/v/verify/";
    private static final String GET_USER_DTO_ENDPOINT_URL = "http://localhost:8080/flowershop/rest/v/usersDto/";
    private static final String UPDATE_BALANCE_USER_DTO_ENDPOINT_URL = "http://localhost:8080/flowershop/rest/v/promo";
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringRestClient client = new SpringRestClient();
        client.restTemplate = new RestTemplate();
        UserWsDTO userWsDTO = new UserWsDTO(3L,BigDecimal.valueOf(2000));
        client.updateDiscountUser(userWsDTO);
    }

    @Before
    public void beforeTest() {
        restTemplate = new RestTemplate();
    }


    @Test
    public void testUpdateDiscountUser() {
        UserWsDTO userWsDTO = new UserWsDTO(2L,BigDecimal.valueOf(2000));
        Assert.assertEquals(updateDiscountUser(userWsDTO),"OK");

        UserWsDTO userWsDTO2 = new UserWsDTO(44L,BigDecimal.valueOf(2000));
        Assert.assertEquals(updateDiscountUser(userWsDTO2),"failure");


    }

    @Test
    public void testValidLogin() {
        Assert.assertTrue(verify("user"));
        Assert.assertFalse(verify("@wwewq"));
    }

    @Test
    public void testValidUserWsDto() {
        Assert.assertEquals((long) getUserById(2l).getId(), 2L);
        Assert.assertNotEquals((long) getUserById(2l).getId(), 1L);
    }

    private String updateDiscountUser(UserWsDTO userWsDTO) {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserWsDTO> user = new HttpEntity<>(userWsDTO,requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(UPDATE_BALANCE_USER_DTO_ENDPOINT_URL, HttpMethod.POST, user, String.class);
        System.out.println("Full response: " + response.getBody());
        return response.getBody();
    }

    private UserWsDTO getUserById(Long id) {
        UserWsDTO result = restTemplate.getForObject(
                GET_USER_DTO_ENDPOINT_URL + id, UserWsDTO.class);
        return result;
    }

    private boolean verify(String login) {
        boolean flag = restTemplate.getForObject(
                GET_VERIFY_ENDPOINT_URL + login, Boolean.class);
        return flag;
    }
}
