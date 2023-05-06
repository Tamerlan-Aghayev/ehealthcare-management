package com.company.ITest;
import com.company.DTOs.ResponseDTO;
import com.company.DemoApplication;
import com.company.entities.Admin;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.boot.web.server.LocalServerPort;


import org.springframework.http.*;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration;
import org.springframework.security.oauth2.client.test.OAuth2ContextSetup;
import org.springframework.security.oauth2.client.test.RestTemplateHolder;

import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class}, webEnvironment = WebEnvironment.DEFINED_PORT)
@OAuth2ContextConfiguration(MyDetails.class)
public class AdminControllerITest implements RestTemplateHolder{
    @LocalServerPort
    private int port;

    public int getPort(){
        return port;
    }

    @Rule
    public OAuth2ContextSetup context = OAuth2ContextSetup.standard(this);

    RestOperations restTemplate;

    @Test

    public void getAdmins() throws Exception {

        String url = "http://localhost:" + port + "/admins";
        System.out.println("url="+url);
        ResponseDTO resp = this.restTemplate.getForObject(url, ResponseDTO.class);

        System.out.println("found="+resp.getObject());
        if(resp.getObject() instanceof List)
        Assert.assertEquals(4, ((List<?>) resp.getObject()).size());
        else fail();

    }
    @Test

    public void getAdmin(){
        String url="http://localhost:" + port + "/admin";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("username", "tamerlan_aghayev");


        String requestBody="id=1";
        ResponseDTO resp=restTemplate.getForObject(builder.toUriString(), ResponseDTO.class);
        if(resp.getObject() instanceof Admin)
            Assert.assertEquals("must be 1", 1,(long) ((Admin) resp.getObject()).getId());
    }
    @Test

    public void InsertAdmin(){
        String url="http://localhost:" + port + "/admin";
        Admin admin=new Admin(12,  "test", "test","test","test","test");
        ResponseEntity<ResponseDTO> responseEntity=this.restTemplate.postForEntity(url, admin, ResponseDTO.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void DeleteAdmin(){
        String url="http://localhost:" + port + "/admin/?id={id}";
        Map<String, Integer> map =new HashMap<>();
        map.put("id", 10);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        restTemplate.exchange(url, HttpMethod.DELETE, null, ResponseDTO.class, map);
    }
    @Override
    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public RestOperations getRestTemplate() {
        return restTemplate;
    }
}


