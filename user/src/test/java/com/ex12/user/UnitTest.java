package com.ex12.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserController userController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void contextLoads () {
        assertThat(userController).isNotNull();
    }

    @Test
    void restTemplateTest() throws Exception {
        this.restTemplate.getForObject("http://localhost:" + port + "/user/get/" + userController.getUser().getId(), User.class);
        assertThat(userController.getUser().getName()).contains("Amelia");
        assertThat(userController.getUser().getSurname()).contains("Grey");
        assertThat(userController.getUser().getEmail()).contains("a.grey@gmail.com");
        assertThat(userController.getUser().getPassword()).contains("MyPsw123");
    }

    @Test
    void MvcUserTest () throws Exception {
        this.mockMvc.perform(get("/user/mock"))
                .andExpect(jsonPath("$.name").value("Amelia"))
                .andExpect(jsonPath("$.surname").value("Grey"))
                .andExpect(jsonPath("$.email").value("a.grey@gmail.com"))
                .andExpect(jsonPath("$.password").value("MyPsw123"))
                .andDo(print())
                .andExpect(status().isOk());
    }





        }