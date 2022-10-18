package com.example.agreementhandler.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgreementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() throws Exception {
        mockMvc.perform(post("/agreements")
                .content("{\"clientId\":\"1100\",\"productId\":\"1200\",\"amount\":\"123.45\",\"startDate\":\"2019-01-01\"}")
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(post("/agreements")
                .content("{\"clientId\":\"2100\",\"productId\":\"2200\",\"amount\":\"223.45\",\"startDate\":\"2019-01-02\"}")
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(post("/agreements")
                .content("{\"clientId\":\"3100\",\"productId\":\"3200\",\"amount\":\"323.45\",\"startDate\":\"2019-01-03\"}")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void create_basic() throws Exception {
        mockMvc.perform(post("/agreements")
                .content("{\"clientId\":\"4100\",\"productId\":\"4200\",\"amount\":\"423.45\",\"startDate\":\"2019-01-04\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId", is(4100)))
                .andExpect(jsonPath("$.productId", is(4200)))
                .andExpect(jsonPath("$.amount", is(423.45)))
                .andExpect(jsonPath("$.startDate", is("2019-01-04")));
    }

    @Test
    public void create_badRequest() throws Exception {
        mockMvc.perform(post("/agreements")
                .content("{\"productId\":\"4200\",\"amount\":\"423.45\",\"startDate\":\"2019-01-04\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getById_basic() throws Exception {
        mockMvc.perform(get("/agreements/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId", is(1100)))
                .andExpect(jsonPath("$.productId", is(1200)))
                .andExpect(jsonPath("$.amount", is(123.45)))
                .andExpect(jsonPath("$.startDate", is("2019-01-01")));
    }

    @Test
    public void getById_notFound() throws Exception {
        mockMvc.perform(get("/agreements/100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAll_basic() throws Exception {
        mockMvc.perform(get("/agreements").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_basic() throws Exception {
        mockMvc.perform(delete("/agreements/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_notFound() throws Exception {
        mockMvc.perform(delete("/agreements/100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void statistic_basic() throws Exception {
        mockMvc.perform(get("/statistics").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
