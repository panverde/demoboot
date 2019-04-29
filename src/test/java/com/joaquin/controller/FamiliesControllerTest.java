package com.joaquin.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FamiliesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void listAllFamilies() throws Exception{
        this.mvc.perform(get("/families/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=utf-8"));

    }

    @Test
    public void createFamilies() throws  Exception{

        this.mvc.perform(post("/families/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"headOfFamilyParentId\": {\n" +
                        "            \"parentId\": 26\n" +
                        "        },\n" +
                        "        \"familyName\": \"FAMILIAS R2\"\n" +
                        "    }")).andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void updateExistFamilies() throws Exception {

        this.mvc.perform(put("/families/update/9")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"headOfFamilyParentId\": {\n" +
                        "            \"parentId\": 10\n" +
                        "        },\n" +
                        "        \"familyName\": \"FAMILIA KAMEHAME\"\n" +
                        "    }")).andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void deleteFamilies() throws Exception{

        this.mvc.perform(MockMvcRequestBuilders.delete("/families/delete/6"))
                .andExpect(status().isNoContent());

    }
}